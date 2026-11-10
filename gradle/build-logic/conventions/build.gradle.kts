plugins {
    `kotlin-dsl`
}

group = "com.varabyte.kobweb.icons.gradle"

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.dokka.plugin)
    implementation(libs.vanniktech.publish.plugin)
}
