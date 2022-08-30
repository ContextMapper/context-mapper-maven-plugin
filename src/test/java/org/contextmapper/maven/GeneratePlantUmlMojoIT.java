package org.contextmapper.maven;

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import java.util.Arrays;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

/**
 * Parametrized Generator Mojo I-Test.
 */
@MavenJupiterExtension
@MavenRepository
public class GeneratePlantUmlMojoIT {

    private static final String[] FILES = new String[] {
        "Insurance-Example-Model_BC_ContractsTeam.puml",
        "Insurance-Example-Model_BC_CustomerManagementContext_Customers.puml",
        "Insurance-Example-Model_BC_CustomerManagementContext.puml",
        "Insurance-Example-Model_BC_CustomerSelfServiceContext_Acounts.puml",
        "Insurance-Example-Model_BC_CustomerSelfServiceContext_CustomerFrontend.puml",
        "Insurance-Example-Model_BC_CustomerSelfServiceContext.puml",
        "Insurance-Example-Model_BC_CustomersTeam.puml",
        "Insurance-Example-Model_BC_DebtCollection_Debts.puml",
        "Insurance-Example-Model_BC_DebtCollection.puml",
        "Insurance-Example-Model_BC_PolicyManagementContext_Contract.puml",
        "Insurance-Example-Model_BC_PolicyManagementContext_Offers.puml",
        "Insurance-Example-Model_BC_PolicyManagementContext_Products.puml",
        "Insurance-Example-Model_BC_PolicyManagementContext.puml",
        "Insurance-Example-Model_BC_PrintingContext_Printing.puml",
        "Insurance-Example-Model_BC_PrintingContext.puml",
        "Insurance-Example-Model_BC_PrintingContext_Templating.puml",
        "Insurance-Example-Model_BC_ProductsTeam.puml",
        "Insurance-Example-Model_BC_RiskManagementContext.puml",
        "Insurance-Example-Model_BC_RiskManagementContext_Risks.puml",
        "Insurance-Example-Model_ContextMap.puml"
    };

    @MavenTest
    public void fail_missing_input(MavenExecutionResult result) {
        assertThat(result).isFailure();
        // TODO: add more assertions about correct error message
    }

    @MavenTest
    public void generate_plant_uml(MavenExecutionResult result) {
        assertThat(result).isSuccessful();
        Arrays.stream(FILES)
              .forEach(
                  (fileName) -> assertThat(result).project().hasTarget().withFile("generated-sources/puml/" + fileName).exists()
              );
    }
}
