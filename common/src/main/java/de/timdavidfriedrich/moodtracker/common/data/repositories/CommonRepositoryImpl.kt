package de.timdavidfriedrich.moodtracker.common.data.repositories

import de.timdavidfriedrich.moodtracker.common.data.sources.LocalDataSource
import de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers.EmotionLocalMapper
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion
import de.timdavidfriedrich.moodtracker.common.domain.repositories.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommonRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : CommonRepository {
    override fun getAllAvailableEmotions(): Flow<List<Emotion>> {
        return localDataSource.getAllEmotions().map { emotions ->
            emotions.mapNotNull { EmotionLocalMapper.toModel(it) }
        }
    }
}