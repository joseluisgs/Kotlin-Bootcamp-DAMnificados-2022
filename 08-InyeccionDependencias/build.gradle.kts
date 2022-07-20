import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val koinVersion: String = "3.2.0"
// Solo para las anotaciones!!!
val koinKsp: String = "1.0.1"
val kspVersion: String = "1.7.10-1.0.6"

plugins {
    kotlin("jvm") version "1.7.10"
    // Solo para las anotaciones!!!
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Koin for Kotlin apps
    implementation("io.insert-koin:koin-core:$koinVersion")
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    // Koin for JUnit 5
    //testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    // Para las anotaciones!!!
    implementation("io.insert-koin:koin-annotations:$koinKsp")
    ksp("io.insert-koin:koin-ksp-compiler:$koinKsp")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

// Solo para anotaciones!!!
// KSP - To use generated sources
// Para usar notaciones de Koin
/*
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}*/

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}
