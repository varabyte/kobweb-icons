package com.varabyte.kobweb.icons.model.fa

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class FaIconSetTest {
    @Test
    fun methodNames() {
        assertThat(FaIconSet.methodNamer.methodNameFor("address-book")).isEqualTo("FaAddressBook")
    }

    @Test
    fun stylePaths() {
        assertThat(FaIconSet.Style.SOLID.iconsJsonName).isEqualTo("solid")
        assertThat(FaIconSet.Style.SOLID.fontPath(major = 7)).isEqualTo("otfs/Font Awesome 7 Free-Solid-900.otf")
        assertThat(FaIconSet.Style.BRANDS.fontPath(major = 6)).isEqualTo("otfs/Font Awesome 6 Brands-Regular-400.otf")
    }
}
