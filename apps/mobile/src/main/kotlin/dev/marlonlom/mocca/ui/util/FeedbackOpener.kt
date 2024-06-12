/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.ui.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import dev.marlonlom.mocca.R

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
    try {
      val uri = Uri.parse(context.getString(R.string.url_settings_feedback_market, context.packageName))
      val goToMarketIntent = Intent(Intent.ACTION_VIEW, uri).also {
        it.addFlags(
          Intent.FLAG_ACTIVITY_NO_HISTORY or
            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
      }
      context.startActivity(goToMarketIntent)
    } catch (exception: ActivityNotFoundException) {
      val goToWebIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(context.getString(R.string.url_settings_feedback_website, context.packageName))
      )
      context.startActivity(goToWebIntent)
    }
  }

}
