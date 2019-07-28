package net.adadev.browser5g.js

import com.anthonycr.mezzanine.FileStream

/**
 * Invert the color of the page.
 */
@FileStream("app/src/main/js/InvertPage.js")
interface InvertPage {

    fun provideJs(): String

}