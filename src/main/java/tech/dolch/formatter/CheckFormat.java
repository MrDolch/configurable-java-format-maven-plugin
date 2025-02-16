package tech.dolch.formatter;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "check")
public class CheckFormat extends MojoImplementation {
  @Override
  public void execute() throws MojoExecutionException {
    checkFormat();
  }
}
