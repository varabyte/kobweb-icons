package com.varabyte.kobweb.icons.model

/**
 * Turns raw icon names into the Kotlin function names, by capitalizing each part between [separator]s and adding
 * [prefix], e.g. "arrow-right" -> "LucideArrowRight".
 *
 * This naming rule is used by all Kobweb icon bindings.
 */
class IconMethodNamer(val prefix: String, val separator: Char) {
    fun nameFor(rawName: String): String {
        return prefix + rawName.split(separator).joinToString("") { part -> part.replaceFirstChar { it.uppercase() } }
    }

    /**
     * Keeps only the longest of any raw names that differ just by [separator]s, e.g. "print-shop" over "printshop".
     *
     * Upstream sets sometimes list both spellings as aliases of one icon. Keeping both would generate near-identical
     * functions (e.g. `FaPrintShop` and `FaPrintshop`), and the separators make for the more readable one.
     */
    fun dropSeparatorCollisions(rawNames: Collection<String>): List<String> {
        return rawNames
            .groupBy { it.replace(separator.toString(), "") }
            .map { (_, names) -> names.maxBy { it.length } }
    }
}
