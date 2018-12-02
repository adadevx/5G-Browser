package acr.browser.speedbrowser7g.di

import acr.browser.speedbrowser7g.BrowserApp
import acr.browser.speedbrowser7g.adblock.AssetsAdBlocker
import acr.browser.speedbrowser7g.adblock.NoOpAdBlocker
import acr.browser.speedbrowser7g.browser.SearchBoxModel
import acr.browser.speedbrowser7g.browser.activity.BrowserActivity
import acr.browser.speedbrowser7g.browser.activity.ThemableBrowserActivity
import acr.browser.speedbrowser7g.browser.fragment.BookmarksFragment
import acr.browser.speedbrowser7g.browser.fragment.TabsFragment
import acr.browser.speedbrowser7g.dialog.LightningDialogBuilder
import acr.browser.speedbrowser7g.download.DownloadHandler
import acr.browser.speedbrowser7g.download.LightningDownloadListener
import acr.browser.speedbrowser7g.reading.activity.ReadingActivity
import acr.browser.speedbrowser7g.search.SuggestionsAdapter
import acr.browser.speedbrowser7g.settings.activity.SettingsActivity
import acr.browser.speedbrowser7g.settings.activity.ThemableSettingsActivity
import acr.browser.speedbrowser7g.settings.fragment.*
import acr.browser.speedbrowser7g.utils.ProxyUtils
import acr.browser.speedbrowser7g.view.LightningChromeClient
import acr.browser.speedbrowser7g.view.LightningView
import acr.browser.speedbrowser7g.view.LightningWebClient
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
