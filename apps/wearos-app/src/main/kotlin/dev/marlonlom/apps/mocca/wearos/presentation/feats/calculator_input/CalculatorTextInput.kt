/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.feats.calculator_input

import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import dev.marlonlom.apps.mocca.wearos.R

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

  Column(
    modifier = Modifier.padding(20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(20.dp))
    InputMoneyAmountOutlinedChip(
      moneyInputState = moneyInputState,
      moneyInputKey = moneyInputKey,
      moneyInputTitle = moneyInputTitle,
      moneyAmountInputActivityLauncher = moneyAmountInputActivityLauncher
    )
    PerformCalculationButton(
      moneyInputState = moneyInputState,
      onMoneyAmountReadyAction = onMoneyAmountReadyAction
    )
  }
}

/**
 * Money amount input outlined chip composable.
 *
 * @author marlonlom
 *
 * @param moneyInputState Money input state.
 * @param moneyInputKey Money input key for retrieval after using keyboard for adding amount text.
 * @param moneyInputTitle Money input title for amount text keyboard input.
 * @param moneyAmountInputActivityLauncher Activity launcher for amount text keyboard input.
 */
@Composable
private fun InputMoneyAmountOutlinedChip(
  moneyInputState: MutableState<String>,
  moneyInputKey: String,
  moneyInputTitle: String,
  moneyAmountInputActivityLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
  OutlinedChip(
    modifier = Modifier.fillMaxWidth(),
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
    }
  )
}

/**
 * Money input value title text composable.
 *
 * @author marlonlom
 *
 */
@Composable
private fun InputMoneyAmountTitle() {
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
private fun MoneyInputValueLabel(
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
private fun PerformCalculationButton(
  moneyInputState: MutableState<String>,
  onMoneyAmountReadyAction: (String) -> Unit
) {
  Button(
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
