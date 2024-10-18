plugins {
    kotlin("jvm")
    idea
}
group = "com.stochastictinkr"
version = "0.1-SNAPSHOT"
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":"))
}
