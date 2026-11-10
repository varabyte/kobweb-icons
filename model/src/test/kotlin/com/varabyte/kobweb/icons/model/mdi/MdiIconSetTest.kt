package com.varabyte.kobweb.icons.model.mdi

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class MdiIconSetTest {
    @Test
    fun methodNames() {
        assertThat(MdiIconSet.methodNamer.methodNameFor("arrow_back")).isEqualTo("MdiArrowBack")
    }

    @Test
    fun stylePaths() {
        assertThat(MdiIconSet.Style.FILLED.codepointsPath).isEqualTo("font/MaterialIcons-Regular.codepoints")
        assertThat(MdiIconSet.Style.FILLED.fontPath).isEqualTo("font/MaterialIcons-Regular.ttf")
        assertThat(MdiIconSet.Style.TWO_TONED.codepointsPath).isEqualTo("font/MaterialIconsTwoTone-Regular.codepoints")
        assertThat(MdiIconSet.Style.TWO_TONED.fontPath).isEqualTo("font/MaterialIconsTwoTone-Regular.otf")
    }
}
