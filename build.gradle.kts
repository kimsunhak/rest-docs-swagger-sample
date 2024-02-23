import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")

    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.asciidoctor.jvm.convert")
    id("com.epages.restdocs-api-spec")
    id("org.hidetake.swagger.generator") version "2.18.2"
}

group = "${property("projectGroup")}"
version = "${property("applicationVersion")}"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    create("asciidoctorExtension")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("swaggerVersion")}")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:${property("epagesRestDocsApiSpecVersion")}")

    "asciidoctorExtension"("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "${project.property("javaVersion")}"
    }
}

tasks.asciidoctor {
	configurations("asciidoctorExtension")
    baseDirFollowsSourceFile()
	dependsOn("restDocsTest")
}

tasks.test {
    useJUnitPlatform {
        excludeTags("restdocs")
    }
}

tasks.register<Test>("restDocsTest") {
    group = "verification"
	useJUnitPlatform {
		includeTags("restdocs")
	}
}

tasks.withType<GenerateSwaggerUI> {
    dependsOn("openapi3")
}

tasks.register<Copy>("copySwagger") {
    delete("src/main/resources/static/docs/openapi3.json")
    from("build/resources/main/static/docs/openapi3.json")
    into("src/main/resources/static/docs")
    dependsOn("openapi3")
}

openapi3 {
    setServer("http://localhost:8080")
    title = "Sample RestDocs Swagger"
    description = "Sample RestDocs"
    version = "0.1"
    format = "json"
    outputDirectory = "build/resources/main/static/docs"
}

