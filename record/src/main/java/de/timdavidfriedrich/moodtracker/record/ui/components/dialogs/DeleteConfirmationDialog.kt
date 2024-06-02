package de.timdavidfriedrich.moodtracker.record.ui.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.timdavidfriedrich.moodtracker.common.ui.components.AttentionButton
import de.timdavidfriedrich.moodtracker.record.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction

@Composable
fun DeleteConfirmationDialog(
    onAction: (RecordAction) -> Unit,
) {
    AlertDialog(
        confirmButton = {
            Button(
                onClick = { onAction(RecordAction.CancelDeleteRecord) }
            ) {
                Text(text = stringResource(R.string.cancel_label))
            }
        },
        dismissButton = {
            AttentionButton(
                onClick = { onAction(RecordAction.DeleteRecord) },
            ) {
                Text(text = stringResource(R.string.delete_label))
            }
        },
        onDismissRequest = { onAction(RecordAction.CancelDeleteRecord) },
        title = { Text(stringResource(R.string.delete_confirmation_title)) },
        text = { Text(stringResource(R.string.delete_confirmation_text)) },
    )
}

@Preview
@Composable
private fun DeleteConfirmationDialogPreview() {
    DeleteConfirmationDialog(onAction = {})
}