package acr.browser.speedbrowser7g.browser

import acr.browser.speedbrowser7g.database.Bookmark

interface BookmarksView {

    fun navigateBack()

    fun handleUpdatedUrl(url: String)

    fun handleBookmarkDeleted(bookmark: Bookmark)

}
