# Kobweb Icons

Information about icon sets (Font Awesome, Material Icons, Material Symbols and Lucide) that:
* [Kobweb](https://github.com/varabyte/kobweb) creates function bindings for
* the [Kobweb IntelliJ plugin](https://github.com/varabyte/kobweb-intellij-plugin) uses for generating icon previews

This lets both projects share one source of truth.

This library is versioned separately from Kobweb, so each project can pick up updates on its own schedule.

## Artifacts

| Artifact | Contents |
|---|---|
| `com.varabyte.kobweb.icons:model` | Facts about each icon set (repos, file paths, styles, CSS classes), plus the rule for naming icon functions. Depends only on the Kotlin stdlib. |
| `com.varabyte.kobweb.icons:parsers` | Parsers for each icon set's upstream files. |

```kotlin
dependencies {
    val version = "x.y.z" // Latest: https://central.sonatype.com/namespace/com.varabyte.kobweb.icons
    implementation("com.varabyte.kobweb.icons:model:$version")
    implementation("com.varabyte.kobweb.icons:parsers:$version")
}
```

Neither artifact downloads anything. Fetching upstream files, and choosing which versions to fetch, are intentionally
left to the caller.
