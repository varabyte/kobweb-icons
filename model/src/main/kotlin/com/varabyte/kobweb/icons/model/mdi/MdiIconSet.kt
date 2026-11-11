package com.varabyte.kobweb.icons.model.mdi

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet

/**
 * Google's Material Icons (https://developers.google.com/fonts/docs/material_icons).
 *
 * This is a legacy set. Google recommends Material Symbols instead.
 */
object MdiIconSet : IconSet {
    /** Material Icons' styles, each with its own font. */
    enum class Style(override val cssClass: String) : IconSet.Style {
        FILLED("material-icons"),
        OUTLINED("material-icons-outlined"),
        ROUNDED("material-icons-round"),
        SHARP("material-icons-sharp"),
        TWO_TONED("material-icons-two-tone");

        /** The style's Google Fonts font family, e.g. "Material Icons Outlined". */
        val fontFamily: String
            get() = cssClass.split('-').joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }

        // The repo names the style's files after its font family, e.g. "font/MaterialIconsOutlined-Regular".
        private val fileStem: String get() = "font/${fontFamily.replace(" ", "")}-Regular"

        /** The style's codepoints file in the repo, which lists its icon names. */
        val codepointsPath: String get() = "$fileStem.codepoints"

        /** The style's font file in the repo. */
        val fontPath: String get() = "$fileStem.${if (this == FILLED) "ttf" else "otf"}"
    }

    override val displayName = "Material Icons"

    /**
     * The repo for both Material Icons and Material Symbols. It started as the Material Icons repo, and Material
     * Symbols was added to it later. It has no release tags.
     */
    override val gitHubRepo = "google/material-design-icons"
    override val styles: List<Style> get() = Style.entries
    override val methodNamer = IconMethodNamer(prefix = "Mdi", separator = '_')
}
