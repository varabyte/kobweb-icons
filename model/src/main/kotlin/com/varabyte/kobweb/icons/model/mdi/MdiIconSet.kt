package com.varabyte.kobweb.icons.model.mdi

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet

/**
 * Google's Material Icons (https://developers.google.com/fonts/docs/material_icons).
 *
 * This is a legacy set. Google recommends Material Symbols instead.
 */
object MdiIconSet : IconSet {
    /**
     * Material Icons' styles, each with its own font.
     *
     * @property fontName The base name of the style's files in the repo, e.g. "MaterialIconsOutlined-Regular".
     * @property fontExtension The extension of the style's font file, which upstream ships as "ttf" for the filled
     *   style and "otf" for the rest.
     */
    enum class Style(override val cssClass: String, val fontName: String, val fontExtension: String) : IconSet.Style {
        FILLED("material-icons", "MaterialIcons-Regular", "ttf"),
        OUTLINED("material-icons-outlined", "MaterialIconsOutlined-Regular", "otf"),
        ROUNDED("material-icons-round", "MaterialIconsRound-Regular", "otf"),
        SHARP("material-icons-sharp", "MaterialIconsSharp-Regular", "otf"),
        TWO_TONED("material-icons-two-tone", "MaterialIconsTwoTone-Regular", "otf");

        private val fileStem: String get() = "font/$fontName"

        /** The style's codepoints file in the repo, which lists its icon names. */
        val codepointsPath: String get() = "$fileStem.codepoints"

        /** The style's font file in the repo. */
        val fontPath: String get() = "$fileStem.$fontExtension"
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
