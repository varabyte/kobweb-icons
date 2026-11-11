package com.varabyte.kobweb.icons.model

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class IconMethodNamerTest {
    @Test
    fun capitalizesEachPartAndAddsPrefix() {
        val namer = IconMethodNamer(prefix = "Lucide", separator = '-')
        assertThat(namer.nameFor("arrow-right")).isEqualTo("LucideArrowRight")
        assertThat(namer.nameFor("arrow-1")).isEqualTo("LucideArrow1")
    }

    @Test
    fun onlySplitsOnTheSeparator() {
        val namer = IconMethodNamer(prefix = "Ms", separator = '_')
        assertThat(namer.nameFor("arrow_back")).isEqualTo("MsArrowBack")
        assertThat(namer.nameFor("arrow-back")).isEqualTo("MsArrow-back")
    }

    @Test
    fun dropSeparatorCollisionsKeepsTheLongestName() {
        val namer = IconMethodNamer(prefix = "Fa", separator = '-')
        assertThat(namer.dropSeparatorCollisions(listOf("printshop", "print-shop", "bell", "bell-slash")))
            .containsExactly("print-shop", "bell", "bell-slash")
    }

    @Test
    fun dropSeparatorCollisionsOnlyLooksAtTheSeparator() {
        val namer = IconMethodNamer(prefix = "Ms", separator = '_')
        assertThat(namer.dropSeparatorCollisions(listOf("print_shop", "printshop", "print-shop")))
            .containsExactly("print_shop", "print-shop")
    }
}
