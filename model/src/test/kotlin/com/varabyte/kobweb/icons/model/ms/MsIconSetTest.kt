package com.varabyte.kobweb.icons.model.ms

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class MsIconSetTest {
    @Test
    fun methodNames() {
        assertThat(MsIconSet.methodNamer.nameFor("arrow_back")).isEqualTo("MsArrowBack")
    }

    @Test
    fun stylePaths() {
        assertThat(MsIconSet.Style.OUTLINED.codepointsPath)
            .isEqualTo("variablefont/MaterialSymbolsOutlined[FILL,GRAD,opsz,wght].codepoints")
        assertThat(MsIconSet.Style.OUTLINED.fontPath)
            .isEqualTo("variablefont/MaterialSymbolsOutlined[FILL,GRAD,opsz,wght].ttf")
    }
}
