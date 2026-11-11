package com.varabyte.kobweb.icons.model.material

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class MdiIconSetTest {
    @Test
    fun methodNames() {
        assertThat(MdiIconSet.methodNamer.nameFor("arrow_back")).isEqualTo("MdiArrowBack")
    }

    @Test
    fun fontFamilies() {
        assertThat(MdiIconSet.Style.FILLED.fontFamily).isEqualTo("Material Icons")
        assertThat(MdiIconSet.Style.OUTLINED.fontFamily).isEqualTo("Material Icons Outlined")
        assertThat(MdiIconSet.Style.ROUNDED.fontFamily).isEqualTo("Material Icons Round")
        assertThat(MdiIconSet.Style.SHARP.fontFamily).isEqualTo("Material Icons Sharp")
        assertThat(MdiIconSet.Style.TWO_TONED.fontFamily).isEqualTo("Material Icons Two Tone")
    }

    @Test
    fun stylePaths() {
        assertThat(MdiIconSet.Style.FILLED.codepointsPath).isEqualTo("font/MaterialIcons-Regular.codepoints")
        assertThat(MdiIconSet.Style.FILLED.fontPath).isEqualTo("font/MaterialIcons-Regular.ttf")
        assertThat(MdiIconSet.Style.OUTLINED.fontPath).isEqualTo("font/MaterialIconsOutlined-Regular.otf")
        assertThat(MdiIconSet.Style.ROUNDED.fontPath).isEqualTo("font/MaterialIconsRound-Regular.otf")
        assertThat(MdiIconSet.Style.SHARP.fontPath).isEqualTo("font/MaterialIconsSharp-Regular.otf")
        assertThat(MdiIconSet.Style.TWO_TONED.codepointsPath).isEqualTo("font/MaterialIconsTwoTone-Regular.codepoints")
        assertThat(MdiIconSet.Style.TWO_TONED.fontPath).isEqualTo("font/MaterialIconsTwoTone-Regular.otf")
    }
}
