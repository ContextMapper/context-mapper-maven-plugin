package org.contextmapper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.generator.PlantUMLGenerator;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

/**
 *
 */
@Mojo(
    name = "generate-plant-uml",
    threadSafe = true,
    defaultPhase = LifecyclePhase.GENERATE_RESOURCES
)
public class PlantUmlGeneratorContextMapMojo extends AbstractContextMapperGeneratorMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {

        if (super.isSkip()) {
            super.getLog().info("Skipping Plant UML diagram generation, because it is disabled by property.");
            return;
        }


        validateAndSetupInputAndOutput();
        try {
            StandaloneContextMapperAPI cmAPI = ContextMapperStandaloneSetup.getStandaloneAPI();
            CMLResource cmlResource = cmAPI.loadCML(getInput());
            cmAPI.callGenerator(cmlResource, new PlantUMLGenerator(), getOutput().getAbsolutePath());
        } catch (Exception e) {
            throw new MojoFailureException(e);
        }
    }
}
