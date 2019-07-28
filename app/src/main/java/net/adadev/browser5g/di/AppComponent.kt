package net.adadev.browser5g.di

import net.adadev.browser5g.BrowserApp
import net.adadev.browser5g.adblock.AssetsAdBlocker
import net.adadev.browser5g.adblock.NoOpAdBlocker
import net.adadev.browser5g.browser.SearchBoxModel
import net.adadev.browser5g.browser.activity.BrowserActivity
import net.adadev.browser5g.browser.activity.ThemableBrowserActivity
import net.adadev.browser5g.browser.fragment.BookmarksFragment
import net.adadev.browser5g.browser.fragment.TabsFragment
import net.adadev.browser5g.dialog.LightningDialogBuilder
import net.adadev.browser5g.download.DownloadHandler
import net.adadev.browser5g.download.LightningDownloadListener
import net.adadev.browser5g.reading.activity.ReadingActivity
import net.adadev.browser5g.search.SuggestionsAdapter
import net.adadev.browser5g.settings.activity.SettingsActivity
import net.adadev.browser5g.settings.activity.ThemableSettingsActivity
import net.adadev.browser5g.settings.fragment.*
import net.adadev.browser5g.utils.ProxyUtils
import net.adadev.browser5g.view.LightningChromeClient
import net.adadev.browser5g.view.LightningView
import net.adadev.browser5g.view.LightningWebClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AppBindsModule::class)])
interface AppComponent {

    fun inject(activity: BrowserActivity)

    fun inject(fragment: BookmarksFragment)

    fun inject(fragment: BookmarkSettingsFragment)

    fun inject(builder: LightningDialogBuilder)

    fun inject(fragment: TabsFragment)

    fun inject(lightningView: LightningView)

    fun inject(activity: ThemableBrowserActivity)

    fun inject(advancedSettingsFragment: AdvancedSettingsFragment)

    fun inject(app: BrowserApp)

    fun inject(proxyUtils: ProxyUtils)

    fun inject(activity: ReadingActivity)

    fun inject(webClient: LightningWebClient)

    fun inject(activity: SettingsActivity)

    fun inject(activity: ThemableSettingsActivity)

    fun inject(listener: LightningDownloadListener)

    fun inject(fragment: PrivacySettingsFragment)

    fun inject(fragment: DebugSettingsFragment)

    fun inject(suggestionsAdapter: SuggestionsAdapter)

    fun inject(chromeClient: LightningChromeClient)

    fun inject(downloadHandler: DownloadHandler)

    fun inject(searchBoxModel: SearchBoxModel)

    fun inject(generalSettingsFragment: GeneralSettingsFragment)

    fun inject(displaySettingsFragment: DisplaySettingsFragment)

    fun provideAssetsAdBlocker(): AssetsAdBlocker

    fun provideNoOpAdBlocker(): NoOpAdBlocker

}
