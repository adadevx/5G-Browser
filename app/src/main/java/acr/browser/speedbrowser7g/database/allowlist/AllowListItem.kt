package acr.browser.speedbrowser7g.database.allowlist

/**
 * A model object representing a whitelisted URL.
 */
data class AllowListItem(
    val url: String,
    val timeCreated: Long
)
