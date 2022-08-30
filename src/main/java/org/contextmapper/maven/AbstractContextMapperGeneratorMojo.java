package org.contextmapper.maven;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileNotFoundException;

import static org.contextmapper.maven.FileUtils.resolveFile;
import static org.contextmapper.maven.FileUtils.resolveOutputDirectory;

/**
 * Abstract generator mojo.
 */
public class AbstractContextMapperGeneratorMojo {
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
  @Parameter(defaultValue = "${project.build}/generated-sources/cml", required = true)
  private String outputDirectory;

  private File input;
  private File output;

  public File getOutput() {
    return output;
  }

  public File getInput() {
    return input;
  }

  void validate() throws MojoFailureException {
    try {
      input = resolveFile(this.project, this.inputFile);
    } catch (FileNotFoundException e) {
      throw new MojoFailureException("Could not open input file " + inputFile, e);
    }
    try {
      output = resolveOutputDirectory(this.project, this.outputDirectory);
    } catch (FileNotFoundException e) {
      throw new MojoFailureException("Could not find output directory " + outputDirectory, e);
    }
  }

}
