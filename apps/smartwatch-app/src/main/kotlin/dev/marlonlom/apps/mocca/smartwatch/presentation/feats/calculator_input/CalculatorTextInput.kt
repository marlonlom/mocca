/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.feats.calculator_input

import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.OutlinedChip
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender
import dev.marlonlom.apps.mocca.smartwatch.R

/**
 * Calculator text input section composable.
 *
 * @author marlonlom
 *
 * @param onMoneyAmountReadyAction Action for money amount ready for calculation.
 */
@Composable
fun CalculatorTextInput(
  onMoneyAmountReadyAction: (String) -> Unit
) {
  val numberRegex = remember { Regex("^-?\\d+\$") }
  val moneyInputKey = LocalContext.current.getString(R.string.text_char_sequence_money_input)
  val moneyInputTitle = LocalContext.current.getString(R.string.text_calculator_input_money_amount)
  val moneyInputState = remember { mutableStateOf("0") }

  val moneyAmountInputActivityLauncher = rememberLauncherForActivityResult(
    ActivityResultContracts.StartActivityForResult()
  ) { activityResult ->
    try {
      val results: Bundle = RemoteInput.getResultsFromIntent(activityResult.data)
      val moneyInputValue: CharSequence? = results.getCharSequence(moneyInputKey)
      if (moneyInputValue!!.matches(numberRegex)) {
        moneyInputState.value = moneyInputValue.toString()
      } else {
        moneyInputState.value = "0"
      }
    } catch (exception: Exception) {
      moneyInputState.value = "0"
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      InputMoneyAmountOutlinedChip(
        moneyInputState = moneyInputState,
        onChipClicked = {
          val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
          val remoteInputs: List<RemoteInput> = listOf(
            RemoteInput.Builder(moneyInputKey)
              .setLabel(moneyInputTitle)
              .wearableExtender {
                setEmojisAllowed(false)
                setInputActionType(EditorInfo.IME_ACTION_DONE)
              }.build()
          )
          RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
          moneyAmountInputActivityLauncher.launch(intent)
        },
      )
      PerformCalculationButton(
        moneyInputState = moneyInputState,
        onMoneyAmountReadyAction = onMoneyAmountReadyAction
      )
    }
  }
}

/**
 * Money amount input outlined chip composable.
 *
 * @author marlonlom
 *
 * @param moneyInputState Money input state.
 * @param onChipClicked Action for chip clicked.
 */
@Composable
internal fun InputMoneyAmountOutlinedChip(
  moneyInputState: MutableState<String>,
  onChipClicked: () -> Unit
) {
  OutlinedChip(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("InputMoneyAmountOutlinedChip"),
    shape = RoundedCornerShape(12),
    icon = {
      Icon(
        modifier = Modifier.size(DpSize(width = 20.dp, height = 20.dp)),
        imageVector = Icons.Rounded.AttachMoney,
        contentDescription = null
      )
    },
    label = {
      InputMoneyAmountTitle()
    },
    secondaryLabel = {
      MoneyInputValueLabel(moneyInputState)
    },
    colors = ChipDefaults.chipColors(
      backgroundColor = MaterialTheme.colors.surface,
      contentColor = MaterialTheme.colors.onSurface,
      secondaryContentColor = MaterialTheme.colors.onSurface,
    ),
    onClick = {
      onChipClicked()
    },
  )
}

/**
 * Money input value title text composable.
 *
 * @author marlonlom
 *
 */
@Composable
internal fun InputMoneyAmountTitle() {
  Text(
    modifier = Modifier.fillMaxWidth(),
    text = stringResource(R.string.text_calculator_input_money_amount),
    maxLines = 1,
    textAlign = TextAlign.Right,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.caption3
  )
}

/**
 * Money input value label text composable.
 *
 * @author marlonlom
 *
 * @param moneyInputState Money input state.
 */
@Composable
internal fun MoneyInputValueLabel(
  moneyInputState: MutableState<String>
) {
  Text(
    modifier = Modifier.fillMaxWidth(),
    text = moneyInputState.value,
    maxLines = 1,
    textAlign = TextAlign.Right,
    style = MaterialTheme.typography.caption2
  )
}

/**
 * Calculation button composable.
 *
 * @author marlonlom
 *
 * @param moneyInputState Money input state.
 * @param onMoneyAmountReadyAction Action for money amount ready for calculation.
 */
@Composable
internal fun PerformCalculationButton(
  moneyInputState: MutableState<String>,
  onMoneyAmountReadyAction: (String) -> Unit
) {
  Button(
    modifier = Modifier.testTag("PerformCalculationButton"),
    colors = ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.secondary,
      contentColor = MaterialTheme.colors.onSecondary,
    ),
    enabled = moneyInputState.value.isNotEmpty().and(moneyInputState.value != "0"),
    onClick = {
      onMoneyAmountReadyAction(moneyInputState.value)
    }
  ) {
    Icon(
      modifier = Modifier.size(DpSize(width = 20.dp, height = 20.dp)),
      imageVector = Icons.Rounded.Check,
      contentDescription = null
    )
  }
}
