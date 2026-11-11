package com.varabyte.kobweb.icons.model.fa

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet

/** Font Awesome's free icons (https://fontawesome.com). */
object FaIconSet : IconSet {
    /**
     * @property fontName The style's part of the desktop font file names, e.g. "Free-Solid-900".
     */
    enum class Style(override val cssClass: String, val fontName: String) : IconSet.Style {
        SOLID("fa-solid", "Free-Solid-900"),
        REGULAR("fa-regular", "Free-Regular-400"),
        BRANDS("fa-brands", "Brands-Regular-400");

        /** The style's name as Font Awesome writes it, e.g. "solid". */
        val styleName: String get() = name.lowercase()

        /**
         * The style's desktop font in the repo, e.g. "otfs/Font Awesome 7 Free-Solid-900.otf".
         *
         * @param major The Font Awesome major version, which is part of the file name.
         */
        fun fontPath(major: Int): String = "otfs/Font Awesome $major $fontName.otf"
    }

    override val displayName = "Font Awesome"
    override val gitHubRepo = "FortAwesome/Font-Awesome"
    override val styles: List<Style> get() = Style.entries
    override val methodNamer = IconMethodNamer(prefix = "Fa", separator = '-')

    /** The repo's list of every icon, with each icon's codepoint and free styles. */
    const val ICONS_JSON_PATH = "metadata/icons.json"
}
