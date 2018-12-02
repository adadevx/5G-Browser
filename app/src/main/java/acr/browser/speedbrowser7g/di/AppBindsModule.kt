package acr.browser.speedbrowser7g.di

import acr.browser.speedbrowser7g.adblock.allowlist.AllowListModel
import acr.browser.speedbrowser7g.adblock.allowlist.SessionAllowListModel
import acr.browser.speedbrowser7g.database.allowlist.AdBlockAllowListDatabase
import acr.browser.speedbrowser7g.database.allowlist.AdBlockAllowListRepository
import acr.browser.speedbrowser7g.database.bookmark.BookmarkDatabase
import acr.browser.speedbrowser7g.database.bookmark.BookmarkRepository
import acr.browser.speedbrowser7g.database.downloads.DownloadsDatabase
import acr.browser.speedbrowser7g.database.downloads.DownloadsRepository
import acr.browser.speedbrowser7g.database.history.HistoryDatabase
import acr.browser.speedbrowser7g.database.history.HistoryRepository
import acr.browser.speedbrowser7g.ssl.SessionSslWarningPreferences
import acr.browser.speedbrowser7g.ssl.SslWarningPreferences
import dagger.Binds
import dagger.Module

/**
 * Dependency injection module used to bind implementations to interfaces.
 */
@Module
abstract class AppBindsModule {

    @Binds
    abstract fun provideBookmarkModel(bookmarkDatabase: BookmarkDatabase): BookmarkRepository

    @Binds
    abstract fun provideDownloadsModel(downloadsDatabase: DownloadsDatabase): DownloadsRepository

    @Binds
    abstract fun providesHistoryModel(historyDatabase: HistoryDatabase): HistoryRepository

    @Binds
    abstract fun providesAdBlockAllowListModel(adBlockAllowListDatabase: AdBlockAllowListDatabase): AdBlockAllowListRepository

    @Binds
    abstract fun providesAllowListModel(sessionAllowListModel: SessionAllowListModel): AllowListModel

    @Binds
    abstract fun providesSslWarningPreferences(sessionSslWarningPreferences: SessionSslWarningPreferences): SslWarningPreferences

}
