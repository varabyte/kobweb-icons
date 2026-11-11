package com.varabyte.kobweb.icons.parsers.fa

import com.varabyte.kobweb.icons.model.fa.FaIconSet
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/** Font Awesome's icons, read from its `icons.json` file and keyed by name. */
class FaIcons private constructor(val icons: Map<String, Icon>) {
    data class Icon(val codepoint: Int, val styles: Set<FaIconSet.Style>)

    companion object {
        /** Parses entries like `"house": {"unicode": "f015", "free": ["regular", "solid"], ...}`. */
        fun parse(text: String): FaIcons {
            val icons = Json.parseToJsonElement(text).jsonObject.mapValues { (name, icon) ->
                val fields = icon.jsonObject
                val unicode = fields["unicode"] ?: error("Font Awesome icon \"$name\" has no unicode")
                val styles = fields["free"]?.jsonArray.orEmpty().map { style ->
                    val styleName = style.jsonPrimitive.content
                    FaIconSet.Style.entries.find { it.styleName == styleName }
                        ?: error("Font Awesome icon \"$name\" has an unknown style: $styleName")
                }
                check(styles.isNotEmpty()) { "Font Awesome icon \"$name\" has no styles" }
                Icon(unicode.jsonPrimitive.content.toInt(16), styles.toSet())
            }
            return FaIcons(icons)
        }
    }
}
