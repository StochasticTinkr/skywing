plugins {
    kotlin("jvm") version "1.9.10"
    `java-library`
    `maven-publish`
    idea
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
        }
    }
}

group = "com.stochastictinkr"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin.jvmToolchain(17)

kotlin.compilerOptions {
    optIn.add("kotlin.contracts.ExperimentalContracts")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("io.mockk:mockk:1.13.4")
}

