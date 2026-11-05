/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CreateActionWatchActivityPendingIntentTest {

  private val context: Context = mockk(relaxed = true)
  private val widgetAction = WidgetActivityAction.INPUT_CALCULATION
  private val activityName = "SmartwatchActivity"
  private val packageName = "com.example.app"
  private val fullActivityName = "$packageName.$activityName"

  @Before
  fun setUp() {
    every { context.packageName } returns packageName
    mockkObject(ActivityResolver)
    mockkConstructor(Intent::class)
    mockkStatic(PendingIntent::class)
  }

  @After
  fun tearDown() {
    unmockkAll()
  }

  @Test
  fun `createActionWatchActivityPendingIntent should return PendingIntent when component is found`() {
    val componentName = mockk<ComponentName> {
      every { className } returns fullActivityName
    }
    every { ActivityResolver.findComponent(context, activityName) } returns componentName

    val mockIntent = mockk<Intent>(relaxed = true)
    every { anyConstructed<Intent>().setClassName(any<Context>(), any<String>()) } returns mockIntent
    every { anyConstructed<Intent>().putExtra(any<String>(), any<String>()) } returns mockIntent

    val mockPendingIntent = mockk<PendingIntent>()
    every {
      PendingIntent.getActivity(
        any<Context>(),
        any<Int>(),
        any<Intent>(),
        any<Int>(),
      )
    } returns mockPendingIntent

    val pendingIntent = createActionWatchActivityPendingIntent(context, widgetAction, activityName)
    assertNotNull(pendingIntent)
  }

  @Test(expected = NullPointerException::class)
  fun `createActionWatchActivityPendingIntent should throw exception when component is not found`() {
    every { ActivityResolver.findComponent(context, activityName) } returns null
    createActionWatchActivityPendingIntent(context, widgetAction, activityName)
  }
}
