package de.timdavidfriedrich.moodtracker.common.domain.repositories

import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    fun getAllAvailableEmotions(): Flow<List<Emotion>>
}