/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.core.net.toUri
import dev.marlonlom.mocca.mobile.ui.R

/**
 * Opens the view related to application rating, either via play store app or website.
 *
 * @author marlonlom
 */
object FeedbackOpener {

  /**
   * Perform app rating display.
   *
   * @param context Application context
   */
  fun rate(context: Context) {
    val ctxPackageName = context.packageName
    try {
      val goToMarketIntent = Intent(
        Intent.ACTION_VIEW,
        getUrlAsUri(context, R.string.url_feedback_market, ctxPackageName),
      ).also {
        it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      context.startActivity(goToMarketIntent)
    } catch (exception: ActivityNotFoundException) {
      val goToWebIntent = Intent(
        Intent.ACTION_VIEW,
        getUrlAsUri(context, R.string.url_feedback_website, ctxPackageName),
      )
      context.startActivity(goToWebIntent)
    }
  }

  /**
   * Retrieves a URI from a string resource.
   *
   * @param context The context used to access resources.
   * @param resId The string resource ID for the URL.
   * @param ctxPackageName The package name to be included in the URL string.
   * @return A Uri parsed from the formatted string resource.
   */
  private fun getUrlAsUri(context: Context, @StringRes resId: Int, ctxPackageName: String) =
    context.getString(resId, ctxPackageName).toUri()
}
