package de.timdavidfriedrich.moodtracker.common.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor

@Composable
private fun BaseAttentionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    filled: Boolean = false,
    content: @Composable (RowScope.() -> Unit),
) {
    val disabledAlpha = 0.38f
    val enabledAlpha = 1f
    val alpha = if (enabled) enabledAlpha else disabledAlpha

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = if (filled) {
            ButtonDefaults.outlinedButtonColors().copy(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = disabledAlpha),
                disabledContentColor = MaterialTheme.colorScheme.onError.copy(alpha = disabledAlpha),
            )
        } else {
            ButtonDefaults.outlinedButtonColors().copy(
                contentColor = MaterialTheme.colorScheme.error,
                disabledContentColor = MaterialTheme.colorScheme.error.copy(alpha = disabledAlpha),
            )
        },
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(MaterialTheme.colorScheme.error.copy(alpha = alpha)),
        ),
        content = content,
    )
}


@Composable
fun AttentionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable (RowScope.() -> Unit),
) {
    BaseAttentionButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        content = content,
    )
}

@Composable
fun FilledAttentionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable (RowScope.() -> Unit),
) {
    BaseAttentionButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        filled = true,
        content = content,
    )
}