package nl.ordina.jtech.wmbus.jokeservice.archtest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchTest {

    /**
     * Api --> Service --> Client
     */
    @Test
    public void test() {
        JavaClasses jc = new ClassFileImporter()
                .withImportOption(new com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests())
                .importPackages("nl.ordina.jtech.wmbus");

        Architectures.LayeredArchitecture arch = layeredArchitecture()
                .consideringOnlyDependenciesInLayers()

                .layer("Api").definedBy("..api..")
                .layer("Service").definedBy("..service..")
                .layer("Client").definedBy("..client..")
                .layer("Domain").definedBy("..domain..")

                .whereLayer("Api").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Api")
                .whereLayer("Client").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Service", "Client");
        arch.check(jc);
    }
}
