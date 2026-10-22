/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.util

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

internal class ActivityResolverTest {

  private lateinit var context: Context
  private lateinit var packageManager: PackageManager
  private lateinit var packageInfo: PackageInfo

  private val packageName = "dev.marlonlom.mocca.wearos"

  @Before
  fun setUp() {
    context = mockk()
    packageManager = mockk()
    packageInfo = PackageInfo().apply {
      activities = arrayOf(
        ActivityInfo().apply {
          name = "$packageName.MainActivity"
        },
      )
    }

    every { context.packageName } returns packageName
    every { context.packageManager } returns packageManager
    every {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    } returns packageInfo
  }

  @Test
  fun `Should return non-null ComponentName when activity exists`() {
    val componentName = ActivityResolver.findComponent(context, "MainActivity")
    assertNotNull(componentName)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `Should return null when activity does not exist`() {
    val componentName = ActivityResolver.findComponent(context, "NonExistentActivity")
    assertNull(componentName)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `Should return null when activities array is null`() {
    packageInfo.activities = null
    val componentName = ActivityResolver.findComponent(context, "MainActivity")
    assertNull(componentName)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `Should return null when activities array is empty`() {
    packageInfo.activities = emptyArray()
    val componentName = ActivityResolver.findComponent(context, "MainActivity")
    assertNull(componentName)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }
}
