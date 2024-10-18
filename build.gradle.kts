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

val testJavaAgent = "testJavaAgent"

configurations {
    create(testJavaAgent) {
        description = "Java agents used in tests"
    }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        optIn.add("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    api(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.mockk:mockk:1.13.12")
    testJavaAgent("net.bytebuddy:byte-buddy-agent:1.11.10")
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
    // add java agent used for mockk
    configurations[testJavaAgent].forEach { jvmArgs("-javaagent:$it") }
}


