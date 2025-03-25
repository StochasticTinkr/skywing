plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
    `maven-publish`
    signing
    idea
}

// Publishing configuration:

group = "com.stochastictinkr"
version = "0.1-SNAPSHOT"

java {
    withSourcesJar()
    withJavadocJar()
}


publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
            pom {
                name.set("${group}:${project.name}")
                description.set("A kotlin-friendly API for building AWT and Swing applications.")
                url.set("https://github.com/StochasticTinkr/skywing")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/StochasticTinkr/skywing.git")
                    developerConnection.set("scm:git:ssh://github.com/StochasticTinkr/skywing.git")
                    url.set("https://github.com/StochasticTinkr/skywing")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications[project.name])
}

// Dependencies and test configuration:

repositories {
    mavenCentral()
    mavenLocal()
}

val testJavaAgent = "testJavaAgent"

configurations.create(testJavaAgent) {
    description = "Java agents used in running tests"
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        optIn.add("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
    testImplementation(libs.test.junit.jupiter)
    testRuntimeOnly(libs.test.junit.platform.launcher)
    testImplementation(libs.test.mockk)
    testJavaAgent(libs.test.agent.bytebuddy)
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}


allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}


tasks.test {
    useJUnitPlatform()
    // add java agent used for mockk
    configurations[testJavaAgent].forEach { jvmArgs("-javaagent:$it") }
}


