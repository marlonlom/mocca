/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget

import androidx.glance.wear.AssociateWithGlanceWearWidget
import androidx.glance.wear.GlanceWearWidget
import androidx.glance.wear.GlanceWearWidgetService

/**
 * Glance wear widget service associated with [MoccaCalculatorWidget] for Wear OS.
 *
 * @author marlonlom
 *
 * @see GlanceWearWidgetService
 *
 */
@AssociateWithGlanceWearWidget(MoccaCalculatorWidget::class)
class MoccaCalculatorWidgetService : GlanceWearWidgetService() {

  /**
   * Returns the GlanceWear widget instance for the calculator.
   */
  override val widget: GlanceWearWidget get() = MoccaCalculatorWidget()
}
