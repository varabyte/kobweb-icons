package com.varabyte.kobweb.icons.model.lucide

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class LucideIconSetTest {
    @Test
    fun methodNames() {
        assertThat(LucideIconSet.methodNamer.methodNameFor("arrow-right")).isEqualTo("LucideArrowRight")
    }
}
