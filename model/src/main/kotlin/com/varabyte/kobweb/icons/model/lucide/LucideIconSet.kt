package com.varabyte.kobweb.icons.model.lucide

import com.varabyte.kobweb.icons.model.IconMethodNamer
import com.varabyte.kobweb.icons.model.IconSet

/** Lucide's icons (https://lucide.dev), which are SVGs drawn with strokes. */
object LucideIconSet : IconSet {
    override val displayName = "Lucide"
    override val gitHubRepo = "lucide-icons/lucide"
    override val styles: List<IconSet.Style> get() = emptyList()
    override val methodNamer = IconMethodNamer(prefix = "Lucide", separator = '-')

    /**
     * The top-level repo directory with the icons.
     *
     * Each icon has a `<name>.svg` with its drawing, plus a `<name>.json` of metadata (tags, categories, and so on).
     */
    const val ICONS_DIR = "icons"
}
