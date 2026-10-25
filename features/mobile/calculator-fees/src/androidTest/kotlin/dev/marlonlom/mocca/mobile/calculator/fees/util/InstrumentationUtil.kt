/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.util

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

/**
 * Utility object for retrieving string resources during instrumentation tests.
 *
 * @author marlonlom
 */
internal object InstrumentationUtil {

  /**
   * Returns a formatted localized string for the specified string resource ID.
   *
   * @param resId String resource identifier.
   * @param formatArgs Format arguments to substitute into the string resource.
   * @return The formatted string resource.
   */
  fun getStringRes(@StringRes resId: Int, vararg formatArgs: Any): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId, *formatArgs)

  /**
   * Returns a localized string for the specified string resource ID.
   *
   * @param resId String resource identifier.
   * @return The string resource.
   */
  fun getStringRes(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
