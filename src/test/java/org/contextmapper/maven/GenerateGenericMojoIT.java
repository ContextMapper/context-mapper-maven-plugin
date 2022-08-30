package org.contextmapper.maven;

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import java.io.File;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

/**
 * Parametrized Generator Mojo I-Test.
 */
@MavenJupiterExtension
@MavenRepository
public class GenerateGenericMojoIT {

    @MavenTest
    public void fail_missing_input(MavenExecutionResult result) {
        assertThat(result).isFailure();
        // TODO: add more assertions about correct error message
    }

    @MavenTest
    public void generate_template(MavenExecutionResult result) {
        assertThat(result).isSuccessful();
        assertThat(result).project().hasTarget().withFile("generated-sources/docs/list-of-bcs.md").exists()
                          .hasSameTextualContentAs(
                              new File(result.getMavenProjectResult().getTargetProjectDirectory(), "target/list-of-bcs.md")
                          );
    }
}
