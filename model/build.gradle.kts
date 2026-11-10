plugins {
    alias(libs.plugins.kotlin.jvm)
    id("kobweb-icons-compilation-settings")
    id("kobweb-icons-publication")
}

group = "com.varabyte.kobweb.icons"
version = libs.versions.kobweb.icons.get()

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.truthish)
}

kobwebIconsPublication {
    artifactName.set("Kobweb Icons Model")
    description.set("Facts about external icon sets that Kobweb generates Silk bindings for.")
}
