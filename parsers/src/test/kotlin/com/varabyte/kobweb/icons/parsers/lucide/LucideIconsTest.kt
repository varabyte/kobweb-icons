package com.varabyte.kobweb.icons.parsers.lucide

import com.varabyte.truthish.assertThat
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.test.Test

class LucideIconsTest {
    @Test
    fun parsesSvgElements() {
        val lucide = LucideIcons.parse(
            mapOf(
                "arrow-right.svg" to """
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                         fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M5 12h14" />
                      <path d="m12 5 7 7-7 7" />
                    </svg>
                """.trimIndent(),
                "arrow-right.json" to """{"tags": ["forward"]}""",
            )
        )

        assertThat(lucide.icons).containsExactly(
            "arrow-right" to LucideIcons.Icon(
                listOf(
                    SvgElement("path", mapOf("d" to "M5 12h14")),
                    SvgElement("path", mapOf("d" to "m12 5 7 7-7 7")),
                ),
                aliases = emptyList(),
                isDeprecated = false,
            ),
        )
    }

    @Test
    fun parsesAliasesAndDeprecations() {
        val lucide = LucideIcons.parse(
            mapOf(
                "alarm-clock-check.svg" to """<svg><circle cx="12" cy="13" r="8" /></svg>""",
                "alarm-clock-check.json" to """
                    {
                      "tags": ["reminder"],
                      "aliases": [
                        {"name": "alarm-check", "deprecationReason": "alias.name", "deprecated": true},
                        {"name": "alarm-done"}
                      ]
                    }
                """.trimIndent(),
                "badge-cent.svg" to """<svg><line x1="12" x2="12" y1="7" y2="17" /></svg>""",
                "badge-cent.json" to """
                    {"tags": ["money"], "deprecated": true, "deprecationReason": "icon.use-case"}
                """.trimIndent(),
            )
        )

        assertThat(lucide.icons).containsExactly(
            "alarm-clock-check" to LucideIcons.Icon(
                listOf(SvgElement("circle", mapOf("cx" to "12", "cy" to "13", "r" to "8"))),
                aliases = listOf(
                    LucideIcons.Alias("alarm-check", isDeprecated = true),
                    LucideIcons.Alias("alarm-done", isDeprecated = false),
                ),
                isDeprecated = false,
            ),
            "badge-cent" to LucideIcons.Icon(
                listOf(SvgElement("line", mapOf("x1" to "12", "x2" to "12", "y1" to "7", "y2" to "17"))),
                aliases = emptyList(),
                isDeprecated = true,
            ),
        )
    }

    @Test
    fun repoZipOnlyReadsTheTopLevelIconsDir() {
        val zipBytes = ByteArrayOutputStream().also { bytes ->
            ZipOutputStream(bytes).use { zip ->
                zip.putNextEntry(ZipEntry("lucide-1.69.0/"))
                zip.putNextEntry(ZipEntry("lucide-1.69.0/icons/"))
                zip.putNextEntry(ZipEntry("lucide-1.69.0/icons/house.svg"))
                zip.write("""<svg><path d="M3 10v11h18V10" /></svg>""".toByteArray())
                zip.putNextEntry(ZipEntry("lucide-1.69.0/icons/house.json"))
                zip.write("""{"tags": ["home"]}""".toByteArray())
                zip.putNextEntry(ZipEntry("lucide-1.69.0/icons/nested/stray.svg"))
                zip.write("""<svg><path d="M0 0" /></svg>""".toByteArray())
                zip.putNextEntry(ZipEntry("lucide-1.69.0/packages/icons/stray.svg"))
                zip.write("""<svg><path d="M0 0" /></svg>""".toByteArray())
            }
        }.toByteArray()

        val lucide = LucideIcons.parseRepoZip(zipBytes.inputStream())

        assertThat(lucide.icons).containsExactly(
            "house" to LucideIcons.Icon(
                listOf(SvgElement("path", mapOf("d" to "M3 10v11h18V10"))),
                aliases = emptyList(),
                isDeprecated = false,
            ),
        )
    }
}
