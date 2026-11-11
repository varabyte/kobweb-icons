package com.varabyte.kobweb.icons.model

/** An external icon set that Kobweb generates bindings for. */
interface IconSet {
    /** One of an icon set's styles, e.g. Font Awesome's solid style. */
    interface Style {
        /** The upstream CSS class that selects this style, e.g. "fa-solid" or "material-icons-outlined". */
        val cssClass: String
    }

    /** The set's name, e.g. "Font Awesome". */
    val displayName: String

    /** The set's GitHub repo, as "owner/name", e.g. "FortAwesome/Font-Awesome". */
    val gitHubRepo: String

    /** The set's styles, or an empty list for sets without any (like Lucide). */
    val styles: List<Style>

    /** Turns the set's raw icon names into function names. */
    val methodNamer: IconMethodNamer
}
