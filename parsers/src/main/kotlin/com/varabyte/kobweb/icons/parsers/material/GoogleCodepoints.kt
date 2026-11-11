package com.varabyte.kobweb.icons.parsers.material

/**
 * A Google icon font's `.codepoints` file, which maps each icon name to its codepoint, keyed by name.
 *
 * Material Icons and Material Symbols both ship one per font.
 */
class GoogleCodepoints private constructor(val codepoints: Map<String, Int>) {
    companion object {
        /** Parses one "name hex" pair per line, e.g. "arrow_back e5c4". */
        fun parse(text: String): GoogleCodepoints {
            val codepoints = text.lineSequence()
                .filter { it.isNotBlank() }
                .associate { line ->
                    val parts = line.trim().split(' ')
                    require(parts.size == 2) { "Unexpected codepoints line: \"$line\"" }
                    parts[0] to parts[1].toInt(16)
                }
            return GoogleCodepoints(codepoints)
        }
    }
}
