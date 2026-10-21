/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile

import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.TimelineBuilders.Timeline.fromLayoutElement
import androidx.wear.protolayout.expression.dynamicDataMapOf
import androidx.wear.protolayout.expression.mapTo
import androidx.wear.protolayout.expression.stringAppDataKey
import androidx.wear.protolayout.material3.MaterialScope
import androidx.wear.protolayout.modifiers.clickable
import androidx.wear.protolayout.modifiers.loadAction
import androidx.wear.tiles.Material3TileService
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import androidx.wear.tiles.tile
import dev.marlonlom.mocca.wearos.calculator.tile.domain.CalculatorStateHolder
import dev.marlonlom.mocca.wearos.calculator.tile.layout.calculatorLayout
import dev.marlonlom.mocca.wearos.calculator.tile.theme.MoccaColorSchemes

/** Key used to store and retrieve the calculator state. */
private const val CALCULATOR_KEY = "calculating_key"

/** Action symbol representing a delete or backspace operation. */
private const val ACTION_DELETE = "-"

/**
 * Tile service that provides quick access to the mocca calculator.
 *
 * @author marlonlom
 *
 */
class MoccaCalculatorTileService :
  Material3TileService(
    allowDynamicTheme = false,
    defaultColorScheme = MoccaColorSchemes.colorScheme,
  ) {

  override suspend fun MaterialScope.tileResponse(requestParams: RequestBuilders.TileRequest): TileBuilders.Tile {
    val state = CalculatorStateHolder.from(
      selectedText = requestParams.currentState.stateMap[stringAppDataKey(CALCULATOR_KEY)],
    )
    val number = state.amount.toInt()
    if (number > 0) {
      android.util.Log.d("MoccaCalculatorTileService", "number=$number")
    }
    return tile(
      timeline = fromLayoutElement(
        calculatorLayout(
          context = this@MoccaCalculatorTileService,
          deviceParameters = deviceConfiguration,
          colorScheme = colorScheme,
          provideInputAmount = { number },
          onNumberButtonClicked = { digit ->
            clickable(
              action = loadAction(
                dynamicDataMapOf(
                  stringAppDataKey(CALCULATOR_KEY) mapTo digit.toString(),
                ),
              ),
            )
          },
          onDeleteButtonClicked = clickable(
            action = loadAction(
              dynamicDataMapOf(
                stringAppDataKey(CALCULATOR_KEY) mapTo ACTION_DELETE,
              ),
            ),
          ),
          onCalculateButtonClicked = ModifiersBuilders.Clickable.Builder()
            .setOnClick(ActionBuilders.LoadAction.Builder().build())
            .build(),
        ),
      ),
    )
  }
}
