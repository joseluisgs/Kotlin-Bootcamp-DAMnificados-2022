import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Variables de iberías para actualizar más facilmente
val kotlinVersion: String = "1.7.10"
val koinVersion: String = "3.2.0"
val koinKspVersion: String = "1.0.1"
val exposedVersion: String = "0.38.2"
val loggingVersion: String = "2.1.23"
val logbackVersion: String = "1.3.0-alpha16"
val h2Version: String = "2.1.212"
val hikariVersion: String = "5.0.1"
val serializationJsonVersion: String = "1.3.3"
val mockkVersion: String = "1.12.4"


plugins {
    kotlin("jvm") version "1.7.10"
    // Para Koin Annotations fallan con algunas cosas de exposed y lo hago manual usando Koin
    // id("com.google.devtools.ksp") version "1.7.10-1.0.6"
    // Serializacion
    kotlin("plugin.serialization") version "1.7.10"
    // Documentación con Dokka
    id("org.jetbrains.dokka") version "1.7.10"
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    //testImplementation("io.insert-koin:koin-test:$koinVersion")
    // Koin annotations, fallan con algunas cosas de exposed y lo hago manual usando Koin sin anotaciones
    //    implementation("io.insert-koin:koin-annotations:$koinKspVersion")
    //    ksp("io.insert-koin:koin-ksp-compiler:$koinKspVersion")
    // Para hacer logs
    implementation("io.github.microutils:kotlin-logging-jvm:$loggingVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // Exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion") // Para usar la fecha en Exposed Podemos usar Kontlix.DateTime https://github.com/Kotlin/kotlinx-datetime
    // BBDD H2  y Hikari
    implementation("com.h2database:h2:$h2Version")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    // Kotlin serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJsonVersion")
    // Test con JUnit y Mockk
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:$mockkVersion")
    // Documentación con Dokka
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:$kotlinVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

// Para el JAR
tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

// Codigo para anotaciones dew Koin
// No las voy a usar y las hago manual usamndo Koin sin anotaciones
/*
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}
*/

