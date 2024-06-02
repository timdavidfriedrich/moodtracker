package de.timdavidfriedrich.moodtracker.common.data.sources

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodGraphDataEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.SongEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.relations.DayRecordWithMomentRecordsRelation
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface LocalDataSource {
    // DayRecord
    suspend fun insertDayRecord(dayRecord: DayRecordEntity)
    suspend fun updateDayRecord(dayRecord: DayRecordEntity)
    fun getAllDayRecords(): Flow<List<DayRecordEntity>>
    fun getAllDayRecordsWithMomentRecords(): Flow<List<DayRecordWithMomentRecordsRelation>>
    fun getDayRecordById(id: Long): Flow<DayRecordEntity?>
    fun getDayRecordWithMomentRecordsById(id: Long): Flow<DayRecordWithMomentRecordsRelation?>
    fun getDayRecordWithMomentRecordsByDateRange(
        startDate: Date,
        endDate: Date,
    ): Flow<DayRecordWithMomentRecordsRelation?>

    suspend fun deleteDayRecord(dayRecord: DayRecordEntity)
    suspend fun deleteDayRecordById(id: Long)

    // Emotion
    suspend fun insertEmotion(emotion: EmotionEntity)
    suspend fun updateEmotion(emotion: EmotionEntity)
    fun getAllEmotions(): Flow<List<EmotionEntity>>
    fun getEmotionById(id: Long): Flow<EmotionEntity?>
    suspend fun deleteEmotion(emotion: EmotionEntity)
    suspend fun deleteEmotionById(id: Long)

    // MomentRecord
    suspend fun insertMomentRecord(momentRecord: MomentRecordEntity)
    suspend fun updateMomentRecord(momentRecord: MomentRecordEntity)
    fun getAllMomentRecords(): Flow<List<MomentRecordEntity>>
    fun getMomentRecordById(id: Long): Flow<MomentRecordEntity?>
    fun getMomentRecordByDateRange(startDate: Date, endDate: Date): Flow<MomentRecordEntity?>
    suspend fun deleteMomentRecord(momentRecord: MomentRecordEntity)
    suspend fun deleteMomentRecordById(id: Long)

    // Mood
    suspend fun insertMood(mood: MoodEntity)
    suspend fun updateMood(mood: MoodEntity)
    fun getAllMoods(): Flow<List<MoodEntity>?>
    fun getMoodById(id: Long): Flow<MoodEntity?>
    suspend fun deleteMood(mood: MoodEntity)
    suspend fun deleteMoodById(id: Long)

    // MoodGraphData
    suspend fun insertMoodGraphData(moodGraphData: MoodGraphDataEntity)
    suspend fun updateMoodGraphData(moodGraphData: MoodGraphDataEntity)
    fun getAllMoodGraphData(): Flow<List<MoodGraphDataEntity>?>
    fun getMoodGraphDataById(id: Long): Flow<MoodGraphDataEntity?>
    suspend fun deleteMoodGraphData(moodGraphData: MoodGraphDataEntity)
    suspend fun deleteMoodGraphDataById(id: Long)

    // Song
    suspend fun insertSong(song: SongEntity)
    suspend fun updateSong(song: SongEntity)
    fun getAllSongs(): Flow<List<SongEntity>?>
    fun getSongById(id: Long): Flow<SongEntity?>
    suspend fun deleteSong(song: SongEntity)
    suspend fun deleteSongById(id: Long)
}