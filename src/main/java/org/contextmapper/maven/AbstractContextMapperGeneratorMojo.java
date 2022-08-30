package org.contextmapper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileNotFoundException;

import static org.contextmapper.maven.FileUtils.resolveInputFile;
import static org.contextmapper.maven.FileUtils.resolveOutputDirectory;

/**
 * Abstract generator mojo.
 */
public abstract class AbstractContextMapperGeneratorMojo extends AbstractMojo {
  /**
   * Property to skip the execution of the mojo.
   */
  @Parameter(property = "context-mapper-generator.skip", defaultValue = "false")
  private Boolean skip = false;

  /**
   * Practical reference to the Maven project
   */
  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  /**
   * Input file containing the CML model.
   */
  @Parameter(required = true)
  private String inputFile;

  /**
   * Output directory to generate the diagrams into.
   */
  @Parameter(defaultValue = "${project.build}/context-mapper", required = true)
  private String outputDirectory;

  private File input;
  private File output;

  public File getOutput() {
    return output;
  }

  public File getInput() {
    return input;
  }

  void validate() throws MojoExecutionException {
    try {
      input = resolveInputFile(this.project, this.inputFile);
    } catch (FileNotFoundException e) {
      throw new MojoExecutionException(e.getMessage());
    }
    try {
      output = resolveOutputDirectory(this.project, this.outputDirectory);
    } catch (FileNotFoundException e) {
      throw new MojoExecutionException(e.getMessage());
    }
  }

}
