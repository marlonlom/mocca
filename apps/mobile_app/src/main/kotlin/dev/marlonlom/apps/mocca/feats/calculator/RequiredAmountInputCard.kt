/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Required amount text input card composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param amountTextState Amount text state.
 * @param onClearedAmountText Action for clearing amount text.
 */
@Composable
fun RequiredAmountInputCard(
  windowSizeUtil: WindowSizeUtil,
  amountTextState: MutableState<String>,
  onClearedAmountText: () -> Unit
) {
  val numberPattern = remember { Regex("^\\d+\$") }

  Card(
    modifier = Modifier
      .fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.secondaryContainer,
      contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
  ) {
    val labelHorizontalPadding = when {
      windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 10.dp)
      else -> PaddingValues(horizontal = 20.dp)
    }

    val labelVerticalPadding = when {
      windowSizeUtil.isTabletLandscape -> PaddingValues(bottom = 0.dp, top = 10.dp)
      else -> PaddingValues(bottom = 0.dp, top = 20.dp)
    }

    val labelTextStyle = when {
      windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.bodySmall
      else -> MaterialTheme.typography.bodyMedium
    }

    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(labelHorizontalPadding)
        .padding(labelVerticalPadding),
      text = stringResource(R.string.text_home_label_amount),
      style = labelTextStyle,
      fontWeight = FontWeight.Normal
    )

    val textFieldStyle = when {
      windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.bodyLarge
      else -> MaterialTheme.typography.titleLarge
    }

    val textFieldBottomPadding = when {
      windowSizeUtil.isTabletLandscape -> PaddingValues(bottom = 0.dp)
      else -> PaddingValues(bottom = 10.dp)
    }

    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(labelHorizontalPadding)
        .padding(textFieldBottomPadding),
      value = amountTextState.value,
      onValueChange = { txt ->
        if ((txt.isEmpty()).or(txt.matches(numberPattern))) {
          amountTextState.value = txt
        }
      },
      colors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent
      ),
      textStyle = textFieldStyle,
      singleLine = true,
      leadingIcon = {
        Icon(
          Icons.Rounded.AttachMoney,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      trailingIcon = {
        if (amountTextState.value.isNotBlank()) {
          IconButton(
            onClick = {
              amountTextState.value = ""
              onClearedAmountText()
            },
          ) {
            Icon(
              Icons.Rounded.Clear,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
          }
        }
      }
    )
  }
}
