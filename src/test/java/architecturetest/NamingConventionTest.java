package architecturetest;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.napptilus.price", importOptions = { ImportOption.DoNotIncludeTests.class })

public class NamingConventionTest {
    @ArchTest
    private final ArchRule application_service_class_names = classes()
            .that()
            .resideInAPackage("..application")
            .and()
            .areTopLevelClasses()
            .should()
            .haveSimpleNameEndingWith("UseCase");

    @ArchTest final ArchRule domain_services_class_names = classes()
            .that()
            .resideInAPackage("..domain.service")
            .should()
            .haveSimpleNameEndingWith("Service");

    @ArchTest final ArchRule domain_repositories_class_names = classes()
            .that()
            .resideInAPackage("..domain.repository")
            .should()
            .haveSimpleNameEndingWith("Repository");

}
