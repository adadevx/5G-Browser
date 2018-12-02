package acr.browser.speedbrowser7g.html

import io.reactivex.Single

/**
 * A factory that builds an HTML page.
 */
interface HtmlPageFactory {

    /**
     * Build the HTML page and emit the URL.
     */
    fun buildPage(): Single<String>

}
