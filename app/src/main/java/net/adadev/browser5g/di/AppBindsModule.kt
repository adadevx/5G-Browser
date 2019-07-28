package net.adadev.browser5g.di

import net.adadev.browser5g.adblock.allowlist.AllowListModel
import net.adadev.browser5g.adblock.allowlist.SessionAllowListModel
import net.adadev.browser5g.database.allowlist.AdBlockAllowListDatabase
import net.adadev.browser5g.database.allowlist.AdBlockAllowListRepository
import net.adadev.browser5g.database.bookmark.BookmarkDatabase
import net.adadev.browser5g.database.bookmark.BookmarkRepository
import net.adadev.browser5g.database.downloads.DownloadsDatabase
import net.adadev.browser5g.database.downloads.DownloadsRepository
import net.adadev.browser5g.database.history.HistoryDatabase
import net.adadev.browser5g.database.history.HistoryRepository
import net.adadev.browser5g.ssl.SessionSslWarningPreferences
import net.adadev.browser5g.ssl.SslWarningPreferences
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
