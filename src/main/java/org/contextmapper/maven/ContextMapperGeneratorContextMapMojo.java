package org.contextmapper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.generator.ContextMapGenerator;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

import java.io.File;
import java.io.FileNotFoundException;

import static org.contextmapper.maven.FileUtils.resolveFile;
import static org.contextmapper.maven.FileUtils.resolveOutputDirectory;

@Mojo(
  name = "generate-context-map",
  threadSafe = true,
  defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class ContextMapperGeneratorContextMapMojo extends AbstractContextMapperGeneratorMojo {


  public void execute() throws MojoExecutionException, MojoFailureException {
    validate();
    StandaloneContextMapperAPI cmAPI = ContextMapperStandaloneSetup.getStandaloneAPI();
    CMLResource cmlResource = cmAPI.loadCML(getInput());
    cmAPI.callGenerator(cmlResource, new ContextMapGenerator(), getOutput().getAbsolutePath());
  }
}
