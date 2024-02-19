package architecturetest;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.napptilus.price", importOptions = { ImportOption.DoNotIncludeTests.class })

public class LayeredArchitectureTest {
    @ArchTest
    private final ArchRule layers_are_not_violated = layeredArchitecture()
            .layer("EntryPoint").definedBy("..entrypoint..")
            .layer("Application").definedBy("..application..")
            .layer("Domain").definedBy("..domain..")
            .layer("Infrastructure").definedBy("..infrastructure..")
            .layer("Shared").definedBy("..shared..")
            .whereLayer("EntryPoint").mayNotBeAccessedByAnyLayer()
            .whereLayer("Application").mayOnlyBeAccessedByLayers("EntryPoint", "Shared")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure", "EntryPoint")
            .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("EntryPoint");
}
