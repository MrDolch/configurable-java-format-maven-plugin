package tech.dolch.formatter;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "format")
public class DoFormat extends MojoImplementation {
  @Override
  public void execute() throws MojoExecutionException {
    doFormat();
  }
}
