package org.contextmapper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
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
    @Parameter(required = true)
    private String outputDirectory;

    private File input;
    private File output;

    /**
     * Retrieves output directory.
     *
     * @return output directory.
     */
    public File getOutput() {
        return output;
    }

    /**
     * Retrieves the input model file.
     *
     * @return input model file.
     */
    public File getInput() {
        return input;
    }

    /**
     * Retrieves the flag to skip execution.
     *
     * @return skip Flag.
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * Retrieves maven project reference.
     *
     * @return read only project reference.
     */
    public MavenProject getProject() {
        return this.project;
    }

    /**
     * Validates and setups input and output for generation.
     * @throws MojoExecutionException if something oes wrong.
     */
    void validateAndSetupInputAndOutput() throws MojoExecutionException {
        try {
            input = resolveInputFile(this.project, this.inputFile, "input");
        } catch (FileNotFoundException e) {
            throw new MojoExecutionException(e.getMessage());
        }
        try {
            output = resolveOutputDirectory(this.project, this.outputDirectory);
        } catch (FileNotFoundException e) {
            throw new MojoExecutionException(e.getMessage());
        }
        setupAdditionalResources();
    }

    protected void setupAdditionalResources() throws MojoExecutionException {

    }

}
