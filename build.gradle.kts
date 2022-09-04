plugins {
    kotlin("jvm") version "1.7.10"
    id("maven-publish")
}
publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
        }
    }
}

group = "com.stochastictinkr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
java {
    withSourcesJar()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

