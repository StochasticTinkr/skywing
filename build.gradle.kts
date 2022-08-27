plugins {
    kotlin("jvm") version "1.7.10"
}

group = "com.stochastictinkr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

