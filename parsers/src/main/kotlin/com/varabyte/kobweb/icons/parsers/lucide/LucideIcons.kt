package com.varabyte.kobweb.icons.parsers.lucide

import com.varabyte.kobweb.icons.model.lucide.LucideIconSet
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.w3c.dom.Element
import java.io.InputStream
import java.util.zip.ZipInputStream
import javax.xml.parsers.DocumentBuilderFactory

/** Lucide's icons, keyed by name. */
class LucideIcons private constructor(val icons: Map<String, Icon>) {
    /**
     * @property elements The elements inside the icon's root `<svg>` element.
     * @property isDeprecated Whether Lucide has deprecated the icon itself, with no replacement.
     */
    data class Icon(val elements: List<SvgElement>, val aliases: List<Alias>, val isDeprecated: Boolean)

    /** Another name for an icon. Deprecated aliases are old names that the icon was renamed from. */
    data class Alias(val name: String, val isDeprecated: Boolean)

    companion object {
        /**
         * Parses the files in Lucide's [LucideIconSet.ICONS_DIR], as file name to contents, e.g. "house.svg" and
         * "house.json".
         */
        fun parse(iconFiles: Map<String, String>): LucideIcons {
            val icons = iconFiles
                .filterKeys { it.endsWith(".svg") }
                .mapKeys { (fileName, _) -> fileName.removeSuffix(".svg") }
                .mapValues { (name, svg) ->
                    val metadata = iconFiles["$name.json"]?.let { Json.parseToJsonElement(it).jsonObject }
                    val aliases = (metadata?.get("aliases") as? JsonArray).orEmpty().map { alias ->
                        val fields = alias.jsonObject
                        Alias(
                            fields.getValue("name").jsonPrimitive.content,
                            isDeprecated = fields["deprecated"]?.jsonPrimitive?.booleanOrNull == true,
                        )
                    }
                    Icon(
                        parseSvgElements(svg),
                        aliases,
                        isDeprecated = metadata?.get("deprecated")?.jsonPrimitive?.booleanOrNull == true,
                    )
                }
            return LucideIcons(icons)
        }

        /**
         * Parses the icons in a zip of Lucide's GitHub repo, as GitHub serves it for a tag or commit, then closes
         * [repoZip].
         *
         * Only files directly inside the top-level [LucideIconSet.ICONS_DIR] are read, since other folders in the repo
         * have `icons` in their paths too.
         */
        fun parseRepoZip(repoZip: InputStream): LucideIcons {
            val iconFiles = mutableMapOf<String, String>()
            ZipInputStream(repoZip).use { zip ->
                for (entry in generateSequence { zip.nextEntry }) {
                    // Everything is inside a root folder named after the repo and ref, e.g.
                    // "lucide-1.69.0/icons/house.svg"
                    val path = entry.name.substringAfter('/')
                    val fileName = path.removePrefix("${LucideIconSet.ICONS_DIR}/")
                    if (entry.isDirectory || fileName == path || '/' in fileName) continue
                    iconFiles[fileName] = zip.readBytes().decodeToString()
                }
            }
            return parse(iconFiles)
        }
    }
}

private fun parseSvgElements(svg: String): List<SvgElement> {
    val factory = DocumentBuilderFactory.newInstance().apply {
        // Never fetch anything an SVG might point to
        setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        setFeature("http://xml.org/sax/features/external-general-entities", false)
        setFeature("http://xml.org/sax/features/external-parameter-entities", false)
    }
    val children = factory.newDocumentBuilder().parse(svg.byteInputStream()).documentElement.childNodes
    return (0 until children.length)
        .map { children.item(it) }
        .filterIsInstance<Element>()
        .map { element ->
            val attributes = element.attributes
            SvgElement(
                element.tagName,
                (0 until attributes.length).map { attributes.item(it) }.associate { it.nodeName to it.nodeValue },
            )
        }
}
