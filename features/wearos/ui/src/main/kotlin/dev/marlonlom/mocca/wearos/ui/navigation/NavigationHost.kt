/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.wear.compose.navigation3.SwipeDismissableSceneStrategy
import androidx.wear.compose.navigation3.rememberSwipeDismissableSceneStrategy

/**
 * Navigation host that wires together app screens using Navigation 3 and Wear Compose navigation.
 *
 * @author marlonlom
 *
 * @param home Composable for the home screen, receiving navigation/action callbacks.
 * @param calculatorInput Composable for entering calculator data, receiving a submit callback.
 * @param calculatorOutput Composable for displaying calculation results, receiving the output and a back callback.
 * @param viewFees Composable for viewing fees, receiving a back callback.
 * @param targetDestination A pair containing the target screen identifier and optional route payload.
 * @param backStack The navigation back stack used to manage app state, defaulting to a swipe-dismissable back stack with Home.
 * @param strategy The scene strategy used for transitions and swipe-to-dismiss behavior.
 */
@Composable
fun NavigationHost(
  home: @Composable (() -> Unit, () -> Unit) -> Unit,
  calculatorInput: @Composable ((String) -> Unit) -> Unit,
  calculatorOutput: @Composable (String, () -> Unit) -> Unit,
  viewFees: @Composable (() -> Unit) -> Unit,
  targetDestination: Pair<Int, String>,
  backStack: NavBackStack<NavKey> = rememberNavBackStack(getNavigationKey(targetDestination)),
  strategy: SwipeDismissableSceneStrategy<NavKey> = rememberSwipeDismissableSceneStrategy(),
) {
  val desiredKey = getNavigationKey(targetDestination)

  LaunchedEffect(targetDestination) {
    if (backStack.isEmpty() || backStack.lastOrNull() != desiredKey) {
      backStack.clear()
      backStack.add(desiredKey)
    }
  }

  NavDisplay(
    backStack = backStack,
    sceneStrategies = listOf(strategy),
    entryProvider = entryProvider {
      entry<NavigationRoute.Home> {
        home(
          {
            backStack.add(NavigationRoute.Calculator)
          },
          {
            backStack.add(NavigationRoute.ViewFees)
          },
        )
      }

      entry<NavigationRoute.Calculator> {
        calculatorInput { amountText ->
          backStack.add(NavigationRoute.Result(amountText))
        }
      }

      entry<NavigationRoute.ViewFees> {
        val activity = LocalActivity.current
        viewFees {
          if (backStack.size <= 1) {
            activity?.finishAndRemoveTask()
          } else {
            backStack.removeLastOrNull()
          }
        }
      }

      entry<NavigationRoute.Result> { route ->
        val activity = LocalActivity.current
        calculatorOutput(
          route.amountText,
        ) {
          if (backStack.size <= 1) {
            activity?.finishAndRemoveTask()
          } else {
            backStack.removeLastOrNull()
          }
        }
      }
    },
  )
}

/**
 * Resolves and returns the corresponding [NavKey] based on the target destination identifier.
 *
 * @author marlonlom
 *
 * @param targetDestination A pair containing the destination ID (first) and its string argument (second).
 * @return The resolved [NavKey] for navigation.
 */
private fun getNavigationKey(targetDestination: Pair<Int, String>): NavKey = when (targetDestination.first) {
  1 -> NavigationRoute.Calculator
  2 -> NavigationRoute.ViewFees
  3 -> NavigationRoute.Result(targetDestination.second)
  else -> NavigationRoute.Home
} as NavKey
