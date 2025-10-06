/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.util

import android.content.Context
import android.os.Build
import dev.marlonlom.mocca.mobile.ui.R

object GeneralUtil {

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
