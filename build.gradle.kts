plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
    idea
}
repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        optIn.add("kotlin.contracts.ExperimentalContracts")
    }
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

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

java {
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    api(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("io.mockk:mockk:1.13.4")
}

