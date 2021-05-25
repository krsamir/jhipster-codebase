package com.demoproject.java;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.demoproject.java");

        noClasses()
            .that()
            .resideInAnyPackage("com.demoproject.java.service..")
            .or()
            .resideInAnyPackage("com.demoproject.java.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.demoproject.java.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
