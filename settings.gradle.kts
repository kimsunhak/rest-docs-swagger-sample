rootProject.name = "jwp-restdocs"

pluginManagement {
    val kotlinVersion:String by settings
    val springBootVersion:String by settings
    val springDependencyManagementVersion: String by settings
    val asciidoctorConvertVersion: String by settings
    val epagesRestDocsApiSpecVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.asciidoctor.jvm.convert") version asciidoctorConvertVersion
        id("com.epages.restdocs-api-spec") version epagesRestDocsApiSpecVersion

        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}