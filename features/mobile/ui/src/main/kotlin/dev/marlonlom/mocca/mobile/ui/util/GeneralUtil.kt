/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.util

import android.content.Context
import android.os.Build
import dev.marlonlom.mocca.mobile.ui.R

/**
 * Utility object containing general-purpose helper functions.
 *
 * @author marlonlom
 *
 */
object GeneralUtil {

  /**
   * Retrieves the formatted application version text.
   *
   * The format is defined in `R.string.app_version_format` and includes both
   * the version name and version code (e.g., "Version 1.2.3 (45)").
   * If the version cannot be determined, a fallback string from
   * `R.string.text_unknown_version` is returned.
   *
   * @param context The application context used to access package information and string resources.
   * @return A formatted version string or a fallback text if unavailable.
   */
  fun getAppVersionText(context: Context): String = try {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName ?: "1.0.0"
    val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      packageInfo.longVersionCode
    } else {
      @Suppress("DEPRECATION")
      packageInfo.versionCode.toLong()
    }
    context.getString(R.string.app_version_format, versionName, versionCode)
  } catch (_: Exception) {
    context.getString(R.string.text_unknown_version)
  }
}
