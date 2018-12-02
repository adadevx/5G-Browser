@file:Suppress("NOTHING_TO_INLINE")

package acr.browser.speedbrowser7g.extensions

import acr.browser.speedbrowser7g.dialog.BrowserDialog
import androidx.appcompat.app.AlertDialog

/**
 * Ensures that the dialog is appropriately sized and displays it.
 */
inline fun AlertDialog.Builder.resizeAndShow() = BrowserDialog.setDialogSize(context, this.show())
