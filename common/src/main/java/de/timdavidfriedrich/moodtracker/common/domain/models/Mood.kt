package de.timdavidfriedrich.moodtracker.common.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveCircleOutline
import androidx.compose.material.icons.rounded.SentimentDissatisfied
import androidx.compose.material.icons.rounded.SentimentNeutral
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material.icons.rounded.SentimentVeryDissatisfied
import androidx.compose.material.icons.rounded.SentimentVerySatisfied

data class Mood(
    val id: Long? = null,
    val score: Double,
) {
    companion object {
        private const val NONE_LIMIT = 0.0
        private const val VERY_NEGATIVE_LIMIT = 0.2
        private const val NEGATIVE_LIMIT = 0.4
        private const val NEUTRAL_LIMIT = 0.6
        private const val POSITIVE_LIMIT = 0.8
        private const val VERY_POSITIVE_LIMIT = 1.0

        fun fromMoodLevel(moodLevel: MoodLevel): Mood {
            return Mood(
                score = when (moodLevel) {
                    is MoodLevel.None -> NONE_LIMIT
                    is MoodLevel.VeryNegative -> VERY_NEGATIVE_LIMIT
                    is MoodLevel.Negative -> NEGATIVE_LIMIT
                    is MoodLevel.Neutral -> NEUTRAL_LIMIT
                    is MoodLevel.Positive -> POSITIVE_LIMIT
                    is MoodLevel.VeryPositive -> VERY_POSITIVE_LIMIT
                }
            )
        }
    }

    val level
        get() = when (score) {
            in NONE_LIMIT..VERY_NEGATIVE_LIMIT -> MoodLevel.VeryNegative
            in VERY_NEGATIVE_LIMIT..NEGATIVE_LIMIT -> MoodLevel.Negative
            in NEGATIVE_LIMIT..NEUTRAL_LIMIT -> MoodLevel.Neutral
            in NEUTRAL_LIMIT..POSITIVE_LIMIT -> MoodLevel.Positive
            in POSITIVE_LIMIT..VERY_POSITIVE_LIMIT -> MoodLevel.VeryPositive
            else -> MoodLevel.None
        }
    val icon
        get() = when (level) {
            is MoodLevel.None -> Icons.Rounded.RemoveCircleOutline
            is MoodLevel.VeryNegative -> Icons.Rounded.SentimentVeryDissatisfied
            is MoodLevel.Negative -> Icons.Rounded.SentimentDissatisfied
            is MoodLevel.Neutral -> Icons.Rounded.SentimentNeutral
            is MoodLevel.Positive -> Icons.Rounded.SentimentSatisfied
            is MoodLevel.VeryPositive -> Icons.Rounded.SentimentVerySatisfied
        }
}

sealed interface MoodLevel {
    data object None : MoodLevel
    data object VeryNegative : MoodLevel
    data object Negative : MoodLevel
    data object Neutral : MoodLevel
    data object Positive : MoodLevel
    data object VeryPositive : MoodLevel
}