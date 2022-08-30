package org.contextmapper.maven;

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

/**
 * Parametrized Generator Mojo I-Test.
 */
@MavenJupiterExtension
public class GenerateMojoIT {

  @MavenTest
  public void generate_context_map(MavenExecutionResult result) {
    assertThat(result).isSuccessful()
      .project()
                      .hasTarget()
                      .withFile("generated-sources/cml/Insurance-Example-Model_ContextMap.svg").exists().isFile()
                      .hasSameTextualContentAs(result.getMavenProjectResult()
                                                     .getTargetBaseDirectory()
                                                     .toPath()
                                                     .resolve("generated-sources/cml/Insurance-Example-Model_ContextMap.svg")
                                                     .toFile())
    ;

  }
}
