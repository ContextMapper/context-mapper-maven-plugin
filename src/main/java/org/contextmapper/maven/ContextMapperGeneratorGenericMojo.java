package org.contextmapper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.generator.GenericContentGenerator;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

import java.io.File;
import java.io.FileNotFoundException;

import static org.contextmapper.maven.FileUtils.resolveInputFile;

@Mojo(
    name = "generate-template",
    threadSafe = true,
    defaultPhase = LifecyclePhase.GENERATE_RESOURCES
)
public class ContextMapperGeneratorGenericMojo extends AbstractContextMapperGeneratorMojo {

    /**
     * File containing the Freemarker Template file for generation.
     */
    @Parameter(required = true)
    private String templateFile;
    /**
     * Filename for the generated result, relative to output dir.
     */
    @Parameter(required = true)
    private String outputFile;

    private File template;

    public void execute() throws MojoExecutionException, MojoFailureException {

        if (isSkip()) {
            getLog().info("Skipping generation using template, because it is disabled by property.");
            return;
        }

        validateAndSetupInputAndOutput();

        getLog().info("Generating context maps from " + getInput().toString());
        getLog().info("Using template " + template.toString());
        getLog().info("Output will is generated to " + getOutput().toString());
        getLog().info("Output filename " + outputFile);

        try {
            StandaloneContextMapperAPI cmAPI = ContextMapperStandaloneSetup.getStandaloneAPI();
            CMLResource cmlResource = cmAPI.loadCML(getInput());
            GenericContentGenerator genericContentGenerator = new GenericContentGenerator();
            genericContentGenerator.setFreemarkerTemplateFile(template);
            genericContentGenerator.setTargetFileName(outputFile);
            cmAPI.callGenerator(cmlResource, genericContentGenerator, getOutput().getAbsolutePath());
        } catch (Exception e) {
            throw new MojoFailureException(e);
        }
    }

    @Override
    protected void setupAdditionalResources() throws MojoExecutionException {
        try {
            template = resolveInputFile(getProject(), this.templateFile, "template");
        } catch (FileNotFoundException e) {
            throw new MojoExecutionException(e.getMessage());
        }

    }
}
