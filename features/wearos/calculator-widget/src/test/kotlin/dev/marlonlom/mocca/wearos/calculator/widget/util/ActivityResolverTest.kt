/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

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
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class ActivityResolverTest {

  private lateinit var context: Context
  private lateinit var packageManager: PackageManager
  private lateinit var packageInfo: PackageInfo

  private val packageName = "com.example.app"
  private val activitySimpleName = "MainActivity"
  private val activityFullName = "$packageName.$activitySimpleName"

  @Before
  fun setUp() {
    context = mockk()
    packageManager = mockk()
    packageInfo = PackageInfo().apply {
      activities = arrayOf(
        ActivityInfo().apply {
          name = activityFullName
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
  fun `findComponent should return ComponentName when matching activity is found`() {
    val result = ActivityResolver.findComponent(context, activitySimpleName)
    assertNotNull(result)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `findComponent should return null when matching activity is not found`() {
    val result = ActivityResolver.findComponent(context, "OtherActivity")
    assertNull(result)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `findComponent should return null when activities array is null`() {
    packageInfo.activities = null
    val result = ActivityResolver.findComponent(context, activitySimpleName)
    assertNull(result)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }

  @Test
  fun `findComponent should return null when activities array is empty`() {
    packageInfo.activities = emptyArray()
    val result = ActivityResolver.findComponent(context, activitySimpleName)
    assertNull(result)
    verify(exactly = 1) {
      packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    }
  }
}
