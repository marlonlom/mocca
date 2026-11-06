/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * Creates a [PendingIntent] to launch the watch activity with a specific widget action.
 *
 * @author marlonlom
 *
 * @param context The context used to resolve the target activity.
 * @param widgetAction The action identifier to be handled by the activity.
 * @param activityName The target activity name. Defaults to "SmartwatchActivity".
 *
 * @return A [PendingIntent] for launching the watch activity.
 */
internal fun createActionWatchActivityPendingIntent(
  context: Context,
  widgetAction: WidgetActivityAction,
  activityName: String = "SmartwatchActivity",
): PendingIntent = ActivityResolver
  .findComponent(context, activityName)!!.className.let { activityClazz ->
  Intent().apply {
    setClassName(context, activityClazz)
    putExtra("widget_action", widgetAction.name)
  }.let { intent ->
    PendingIntent.getActivity(
      context,
      System.currentTimeMillis().toInt() + widgetAction.ordinal,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
  }
}
