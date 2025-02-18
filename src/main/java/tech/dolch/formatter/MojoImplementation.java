package tech.dolch.formatter;

import static difflib.DiffUtils.generateUnifiedDiff;
import static java.nio.charset.Charset.defaultCharset;

import com.google.googlejavaformat.java.Main;
import difflib.DiffUtils;
import difflib.Patch;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

abstract class MojoImplementation extends AbstractMojo {
  private @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true) File
      sourceDirectory;
  private @Parameter(defaultValue = "${project.build.sourceEncoding}") String encoding;

  private @Parameter(property = "aosp", defaultValue = "false") boolean aosp;

  private @Parameter(property = "width", defaultValue = "100") int width;

  private @Parameter(property = "fixImportsOnly", defaultValue = "false") boolean fixImportsOnly;

  private @Parameter(property = "skipSortingImports", defaultValue = "false") boolean
      skipSortingImports;

  private @Parameter(property = "skipRemovingUnusedImports", defaultValue = "false") boolean
      skipRemovingUnusedImports;

  private @Parameter(property = "skipReflowingLongStrings", defaultValue = "false") boolean
      skipReflowingLongStrings;

  private @Parameter(property = "skipJavadocFormatting", defaultValue = "false") boolean
      skipJavadocFormatting;

  public String[] toArgs(boolean inPlace) {
    Stream.Builder<String> argBuilder = Stream.builder();
    if (aosp) argBuilder.add("--aosp");
    if (width >= 0) argBuilder.add("--width=" + width);
    if (fixImportsOnly) argBuilder.add("--fix-imports-only");
    if (skipSortingImports) argBuilder.add("--skip-sorting-imports");
    if (skipRemovingUnusedImports) argBuilder.add("--skip-removing-unused-imports");
    if (skipReflowingLongStrings) argBuilder.add("--skip-reflowing-long-strings");
    if (skipJavadocFormatting) argBuilder.add("--skip-javadoc-formatting");
    if (inPlace) argBuilder.add("-i");
    return argBuilder.build().toArray(String[]::new);
  }

  void checkFormat() throws MojoExecutionException {
    getLog().info("Source Directory: " + sourceDirectory.getPath());
    Charset sourceFileEncoding = encoding == null ? defaultCharset() : Charset.forName(encoding);
    try {
      for (File javaFile : getAllJavaFiles(sourceDirectory)) {
        getLog().info("Checking: " + javaFile.getAbsolutePath());
        String expected = Files.readString(Path.of(javaFile.getAbsolutePath()), sourceFileEncoding);
        String formattedSource = callFormatter(javaFile, toArgs(false));
        if (!expected.equals(formattedSource)) {
          Patch<String> patch =
              DiffUtils.diff(expected.lines().toList(), formattedSource.lines().toList());
          String diff =
              String.join(
                  "\n",
                  generateUnifiedDiff("Expected", "Actual", expected.lines().toList(), patch, 2));
          throw new MojoFailureException(
              javaFile.getPath() + " is not formatted correctly\n" + diff);
        }
      }
    } catch (Exception e) {
      throw new MojoExecutionException("Exception", e);
    }
  }

  static List<File> getAllJavaFiles(File dir) {
    List<File> result = new ArrayList<>();
    if (dir.exists() && dir.isDirectory()) {
      File[] files = dir.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isDirectory()) {
            result.addAll(getAllJavaFiles(file));
          } else if (file.getName().endsWith(".java")) {
            result.add(file);
          }
        }
      }
    }
    return result;
  }

  void doFormat() throws MojoExecutionException {
    try {
      getLog().info("Source Directory: " + sourceDirectory.getAbsolutePath());
      for (File javaFile : getAllJavaFiles(sourceDirectory)) {
        getLog().info("Formatting: " + javaFile.getAbsolutePath());
        callFormatter(javaFile, toArgs(true));
      }
    } catch (Exception e) {
      throw new MojoExecutionException("Exception", e);
    }
  }

  String callFormatter(File javaFile, String... args) throws Exception {
    StringWriter formattedSource = new StringWriter();
    StringWriter error = new StringWriter();
    Main formatter = new Main(new PrintWriter(formattedSource), new PrintWriter(error), System.in);
    List<String> arguments = new ArrayList<>(List.of(args));
    arguments.add(javaFile.getAbsolutePath());
    int returnValue = formatter.format(arguments.toArray(String[]::new));
    if (returnValue != 0) throw new MojoExecutionException("Error: " + error);
    return formattedSource.toString();
  }
}
