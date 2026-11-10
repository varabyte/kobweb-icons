import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

// Target an old JVM, since nothing here needs newer APIs and it keeps the artifacts usable everywhere.
val targetJvm = JvmTarget.JVM_11

// The KIP plugin runs against whatever Kotlin stdlib the IDE bundles, so compile against (and depend on) an old one to
// make sure we never call a stdlib API that the IDE might not have.
val oldestKotlinVersion = "2.0.21"

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = targetJvm.target
    targetCompatibility = targetJvm.target
}

pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    configure<KotlinJvmExtension> {
        compilerOptions {
            jvmTarget = targetJvm
            // See also: https://jakewharton.com/kotlins-jdk-release-compatibility-flag/
            freeCompilerArgs.add("-Xjdk-release=${targetJvm.target}")

            val kotlinVersion = KotlinVersion.fromVersion(oldestKotlinVersion.substringBeforeLast('.'))
            languageVersion = kotlinVersion
            apiVersion = kotlinVersion
            // Suppress warning about deprecated language version
            freeCompilerArgs.add("-Xsuppress-version-warnings")
        }
    }

    // Declaring the stdlib explicitly stops the Kotlin plugin from adding its own (newer) version
    dependencies {
        "api"("org.jetbrains.kotlin:kotlin-stdlib:$oldestKotlinVersion")
    }
}
