package com.varabyte.kobweb.icons.parsers.fa

import com.varabyte.kobweb.icons.model.fa.FaIconSet
import com.varabyte.truthish.assertThat
import com.varabyte.truthish.assertThrows
import kotlin.test.Test

class FaIconsTest {
    @Test
    fun parsesCodepointsAndStyles() {
        val iconsJson = FaIcons.parse(
            """
            {
              "house": {"unicode": "f015", "free": ["regular", "solid"], "styles": ["solid", "regular"]},
              "github": {"unicode": "f09b", "free": ["brands"], "styles": ["brands"]}
            }
            """.trimIndent()
        )

        assertThat(iconsJson.icons).containsExactly(
            "house" to FaIcons.Icon(0xf015, setOf(FaIconSet.Style.REGULAR, FaIconSet.Style.SOLID)),
            "github" to FaIcons.Icon(0xf09b, setOf(FaIconSet.Style.BRANDS)),
        )
    }

    @Test
    fun unknownStylesAreRejected() {
        val e = assertThrows<IllegalStateException> {
            FaIcons.parse("""{"house": {"unicode": "f015", "free": ["duotone"]}}""")
        }
        assertThat(e.message!!).isEqualTo("Font Awesome icon \"house\" has an unknown style: duotone")
    }

    @Test
    fun iconsWithoutStylesAreRejected() {
        val e = assertThrows<IllegalStateException> {
            FaIcons.parse("""{"alien": {"unicode": "f8f5", "free": []}}""")
        }
        assertThat(e.message!!).isEqualTo("Font Awesome icon \"alien\" has no styles")
    }
}
