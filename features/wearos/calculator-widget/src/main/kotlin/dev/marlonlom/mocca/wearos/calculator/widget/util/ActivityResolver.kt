/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

/**
 * Utility object for resolving activity components.
 *
 * @author marlonlom
 */
internal object ActivityResolver {

  /**
   * Finds and returns the [ComponentName] for the given activity name.
   *
   * @param context The Android context.
   * @param activityName The short name of the activity.
   * @return The resolved [ComponentName], or null if not found.
   */
  fun findComponent(context: Context, activityName: String): ComponentName? {
    val packageInfo = context.packageManager.getPackageInfo(
      context.packageName,
      PackageManager.GET_ACTIVITIES,
    )
    val activityInfo = packageInfo.activities
      .orEmpty()
      .firstOrNull {
        it.name.substringAfterLast('.') == activityName
      }
    return activityInfo?.let {
      ComponentName(context.packageName, it.name)
    }
  }
}

/**
 * Represents the available actions that can be triggered from the Wear OS widget.
 *
 * @author marlonlom
 *
 */
internal enum class WidgetActivityAction {
  /** Opens the input calculation screen or flow. */
  INPUT_CALCULATION,

  /** Displays the fees information view. */
  SHOW_FEES,
}
