import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension

plugins {
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish")
}

abstract class KobwebIconsPublicationConfig {
    /** A human-readable display name for this artifact, e.g. "Kobweb Icons Model". */
    abstract val artifactName: Property<String>

    /** The artifact ID, e.g. "kobweb-icons-model". */
    abstract val artifactId: Property<String>

    /** A human-readable description for this artifact. */
    abstract val description: Property<String>
}

val config = extensions.create<KobwebIconsPublicationConfig>("kobwebIconsPublication")

val githubPath = "https://github.com/varabyte/kobweb-icons"

pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    // Opt published artifacts in to the unused return value checker
    // See: https://github.com/Kotlin/KEEP/blob/main/proposals/KEEP-0412-unused-return-value-checker.md#opting-in-your-library
    configure<KotlinJvmExtension> {
        compilerOptions.freeCompilerArgs.add("-Xreturn-value-checker=full")
    }
}

dokka {
    dokkaSourceSets.configureEach {
        sourceLink {
            val path = project.projectDir.relativeTo(layout.settingsDirectory.asFile).invariantSeparatorsPath
            localDirectory = project.projectDir.resolve("src")
            remoteUrl("$githubPath/tree/main/$path/src")
            remoteLineSuffix = "#L"
        }
    }
}

// Wait until the calling build script has set the version and the config values
afterEvaluate {
    mavenPublishing {
        coordinates(artifactId = config.artifactId.get())
        publishToMavenCentral(automaticRelease = true)
        signAllPublications()

        pom {
            name.set(config.artifactName)
            description.set(config.description)
            url.set(githubPath)
            scm {
                url.set(githubPath)
                val connectionPath = "scm:git:${githubPath}.git"
                connection.set(connectionPath)
                developerConnection.set(connectionPath)
            }
            developers {
                developer {
                    id.set("bitspittle")
                    name.set("David Herman")
                    email.set("bitspittle@gmail.com")
                    url.set("https://github.com/bitspittle")
                }
            }
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
        }
    }
}
