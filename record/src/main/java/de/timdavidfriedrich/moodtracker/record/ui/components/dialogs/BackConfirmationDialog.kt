package de.timdavidfriedrich.moodtracker.record.ui.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction

@Composable
fun BackConfirmationDialog(
    onAction: (RecordAction) -> Unit,
) {
    AlertDialog(
        confirmButton = {
            Button(
                onClick = { onAction(RecordAction.CancelBackClick) }
            ) {
                Text(text = stringResource(R.string.stay_label))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onAction(RecordAction.BackClick) },
            ) {
                Text(text = stringResource(R.string.go_back_label))
            }
        },
        onDismissRequest = { onAction(RecordAction.CancelBackClick) },
        title = { Text(stringResource(R.string.back_confirmation_title)) },
        text = { Text(stringResource(R.string.back_confirmation_text)) },
    )
}

@Preview
@Composable
private fun BackConfirmationDialogPreview() {
    BackConfirmationDialog(onAction = {})
}