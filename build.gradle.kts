import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    // id("io.gitlab.arturbosch.detekt") version "1.19.0-RC1"
}

group = "me.yuri0217"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "io.netty", name = "netty-all", version = "4.1.70.Final")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation(group = "io.kotest", name = "kotest-runner-junit5-jvm", version = "4.6.3")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

tasks.withType<Test> {
    useJUnitPlatform()
}