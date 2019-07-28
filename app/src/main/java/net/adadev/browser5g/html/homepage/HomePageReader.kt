package net.adadev.browser5g.html.homepage

import com.anthonycr.mezzanine.FileStream

/**
 * The store for the homepage HTML.
 */
@FileStream("app/src/main/html/homepage.html")
interface HomePageReader {

    fun provideHtml(): String

}