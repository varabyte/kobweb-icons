package com.varabyte.kobweb.icons.model.material

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet

/** Google's Material Symbols (https://developers.google.com/fonts/docs/material_symbols). */
object MsIconSet : IconSet {
    /** Material Symbols' styles, each a separate font family with the same icons. */
    enum class Style(override val cssClass: String) : IconSet.Style {
        OUTLINED("material-symbols-outlined"),
        ROUNDED("material-symbols-rounded"),
        SHARP("material-symbols-sharp");

        /** The style's Google Fonts font family, e.g. "Material Symbols Outlined". */
        val fontFamily: String
            get() = cssClass.split('-').joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }

        // The repo names the style's files after its font family. The bracketed suffix names the variable font's
        // axes.
        private val fileStem: String
            get() = "variablefont/${fontFamily.replace(" ", "")}[FILL,GRAD,opsz,wght]"

        /** The style's codepoints file in the repo, which lists its icon names. */
        val codepointsPath: String get() = "$fileStem.codepoints"

        /** The style's variable font file in the repo. */
        val fontPath: String get() = "$fileStem.ttf"
    }

    override val displayName = "Material Symbols"
    override val gitHubRepo = MdiIconSet.gitHubRepo
    override val styles: List<Style> get() = Style.entries
    override val methodNamer = IconMethodNamer(prefix = "Ms", separator = '_')
}
