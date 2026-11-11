package com.varabyte.kobweb.icons.model.ms

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class MsIconSetTest {
    @Test
    fun methodNames() {
        assertThat(MsIconSet.methodNamer.nameFor("arrow_back")).isEqualTo("MsArrowBack")
    }

    @Test
    fun fontFamilies() {
        assertThat(MsIconSet.Style.OUTLINED.fontFamily).isEqualTo("Material Symbols Outlined")
        assertThat(MsIconSet.Style.ROUNDED.fontFamily).isEqualTo("Material Symbols Rounded")
        assertThat(MsIconSet.Style.SHARP.fontFamily).isEqualTo("Material Symbols Sharp")
    }

    @Test
    fun stylePaths() {
        assertThat(MsIconSet.Style.OUTLINED.codepointsPath)
            .isEqualTo("variablefont/MaterialSymbolsOutlined[FILL,GRAD,opsz,wght].codepoints")
        assertThat(MsIconSet.Style.OUTLINED.fontPath)
            .isEqualTo("variablefont/MaterialSymbolsOutlined[FILL,GRAD,opsz,wght].ttf")
        assertThat(MsIconSet.Style.ROUNDED.fontPath)
            .isEqualTo("variablefont/MaterialSymbolsRounded[FILL,GRAD,opsz,wght].ttf")
        assertThat(MsIconSet.Style.SHARP.fontPath)
            .isEqualTo("variablefont/MaterialSymbolsSharp[FILL,GRAD,opsz,wght].ttf")
    }
}
