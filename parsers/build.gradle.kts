plugins {
    alias(libs.plugins.kotlin.jvm)
    id("kobweb-icons-compilation-settings")
    id("kobweb-icons-publication")
}

group = "com.varabyte.kobweb.icons"
version = libs.versions.kobweb.icons.get()

dependencies {
    api(project(":model"))
    implementation(libs.kotlinx.serialization.json)

    testImplementation(kotlin("test"))
    testImplementation(libs.truthish)
}

kobwebIconsPublication {
    artifactName.set("Kobweb Icons Parsers")
    artifactId.set("kobweb-icons-parsers")
    description.set("Parsers for the upstream files of the icon sets that Kobweb generates Silk bindings for.")
}
