package com.varabyte.kobweb.icons.parsers.material

import com.varabyte.truthish.assertThat
import com.varabyte.truthish.assertThrows
import kotlin.test.Test

class GoogleCodepointsTest {
    @Test
    fun parsesNameHexPairs() {
        val codepoints = GoogleCodepoints.parse(
            """
            10k e951
            arrow_back e5c4
            home e88a

            """.trimIndent()
        )

        assertThat(codepoints.codepoints).containsExactly(
            "10k" to 0xe951,
            "arrow_back" to 0xe5c4,
            "home" to 0xe88a,
        )
    }

    @Test
    fun malformedLinesAreRejected() {
        val e = assertThrows<IllegalArgumentException> {
            GoogleCodepoints.parse("arrow_back e5c4\nhome")
        }
        assertThat(e.message!!).isEqualTo("Unexpected codepoints line: \"home\"")
    }
}
