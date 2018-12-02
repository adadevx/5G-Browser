/*
 * Copyright 2014 A.C.R. Development
 */
package acr.browser.speedbrowser7g.settings.fragment

import acr.browser.speedbrowser7g.BuildConfig
import acr.browser.speedbrowser7g.R
import android.os.Bundle

class AboutSettingsFragment : AbstractSettingsFragment() {

    override fun providePreferencesXmlResource() = R.xml.preference_about

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickablePreference(
            preference = SETTINGS_VERSION,
            summary = BuildConfig.VERSION_NAME,
            onClick = { }
        )
    }

    companion object {
        private const val SETTINGS_VERSION = "pref_version"
    }
}
