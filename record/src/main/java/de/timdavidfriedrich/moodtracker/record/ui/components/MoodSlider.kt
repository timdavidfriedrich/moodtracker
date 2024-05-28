package de.timdavidfriedrich.moodtracker.record.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import de.timdavidfriedrich.moodtracker.common.R
import de.timdavidfriedrich.moodtracker.record.ui.RecordAction
import de.timdavidfriedrich.moodtracker.record.ui.RecordState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodSlider(
    state: RecordState.Success.Moment,
    modifier: Modifier = Modifier,
    onAction: (RecordAction) -> Unit = {},
) {
    Slider(
        colors = SliderDefaults.colors().copy(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
        ),
        thumb = {
            Surface(
                color = MaterialTheme.colorScheme.surfaceContainer,
            ) {
                Image(
                    painter = painterResource(
                        id = state.record.mood?.iconId ?: R.drawable.moodie_positive
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size_medium)),
                )
            }
        },
        value = state.record.mood?.score?.toFloat() ?: 0f,
        onValueChange = { onAction(RecordAction.Moment.MoodSliderChange(it)) },
        modifier = modifier,
    )
}