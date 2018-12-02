package acr.browser.speedbrowser7g.search.engine

import acr.browser.speedbrowser7g.R

/**
 * The DuckDuckGo search engine.
 *
 * See https://duckduckgo.com/assets/logo_homepage.normal.v101.png for the icon.
 */
class DuckSearch : BaseSearchEngine(
    "file:///android_asset/duckduckgo.png",
    "https://duckduckgo.com/?t=a5gfastbrowser&q=",
    R.string.search_engine_duckduckgo
)
