package com.varabyte.kobweb.icons.model.ms

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet
import com.varabyte.kobweb.icons.model.mdi.MdiIconSet

/** Google's Material Symbols (https://developers.google.com/fonts/docs/material_symbols). */
object MsIconSet : IconSet {
    /**
     * Material Symbols' styles, each a separate font family with the same icons.
     *
     * @property familyName The style's font family, which its files in the repo are named after.
     */
    enum class Style(override val cssClass: String, val familyName: String) : IconSet.Style {
        OUTLINED("material-symbols-outlined", "MaterialSymbolsOutlined"),
        ROUNDED("material-symbols-rounded", "MaterialSymbolsRounded"),
        SHARP("material-symbols-sharp", "MaterialSymbolsSharp");

        /** The bracketed suffix names the variable font's axes. */
        private val fileStem: String get() = "variablefont/$familyName[FILL,GRAD,opsz,wght]"

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
