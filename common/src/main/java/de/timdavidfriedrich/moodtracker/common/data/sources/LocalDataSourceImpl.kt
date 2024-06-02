package de.timdavidfriedrich.moodtracker.common.data.sources

import de.timdavidfriedrich.moodtracker.common.data.sources.local.LocalDatabase
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodGraphDataEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.SongEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.relations.DayRecordWithMomentRecordsRelation
import kotlinx.coroutines.flow.Flow
import java.util.Date

class LocalDataSourceImpl(
    private val database: LocalDatabase,
) : LocalDataSource {
    override suspend fun insertDayRecord(dayRecord: DayRecordEntity) {
        database.dayRecordDao().insertDayRecord(dayRecord)
    }

    override suspend fun updateDayRecord(dayRecord: DayRecordEntity) {
        database.dayRecordDao().updateDayRecord(dayRecord)
    }

    override fun getAllDayRecords(): Flow<List<DayRecordEntity>> {
        return database.dayRecordDao().getAllDayRecords()
    }

    override fun getAllDayRecordsWithMomentRecords(): Flow<List<DayRecordWithMomentRecordsRelation>> {
        return database.dayRecordDao().getAllDayRecordsWithMomentRecords()
    }

    override fun getDayRecordById(id: Long): Flow<DayRecordEntity?> {
        return database.dayRecordDao().getDayRecordById(id)
    }

    override fun getDayRecordWithMomentRecordsById(id: Long): Flow<DayRecordWithMomentRecordsRelation?> {
        return database.dayRecordDao().getDayRecordWithMomentRecordsById(id)
    }

    override fun getDayRecordWithMomentRecordsByDateRange(
        startDate: Date,
        endDate: Date,
    ): Flow<DayRecordWithMomentRecordsRelation?> {
        return database.dayRecordDao().getDayRecordWithMomentRecordsByDateRange(startDate, endDate)
    }

    override suspend fun deleteDayRecord(dayRecord: DayRecordEntity) {
        database.dayRecordDao().deleteDayRecord(dayRecord)
    }

    override suspend fun deleteDayRecordById(id: Long) {
        database.dayRecordDao().deleteDayRecordById(id)
    }

    override suspend fun insertEmotion(emotion: EmotionEntity) {
        database.emotionDao().insertEmotion(emotion)
    }

    override suspend fun updateEmotion(emotion: EmotionEntity) {
        database.emotionDao().updateEmotion(emotion)
    }

    override fun getAllEmotions(): Flow<List<EmotionEntity>> {
        return database.emotionDao().getAllEmotions()
    }

    override fun getEmotionById(id: Long): Flow<EmotionEntity?> {
        return database.emotionDao().getEmotionById(id)
    }

    override suspend fun deleteEmotion(emotion: EmotionEntity) {
        database.emotionDao().deleteEmotion(emotion)
    }

    override suspend fun deleteEmotionById(id: Long) {
        database.emotionDao().deleteEmotionById(id)
    }

    override suspend fun insertMomentRecord(momentRecord: MomentRecordEntity) {
        database.momentRecordDao().insertMomentRecord(momentRecord)
    }

    override suspend fun updateMomentRecord(momentRecord: MomentRecordEntity) {
        database.momentRecordDao().updateMomentRecord(momentRecord)
    }

    override fun getAllMomentRecords(): Flow<List<MomentRecordEntity>> {
        return database.momentRecordDao().getAllMomentRecords()
    }

    override fun getMomentRecordById(id: Long): Flow<MomentRecordEntity?> {
        return database.momentRecordDao().getMomentRecordById(id)
    }

    override fun getMomentRecordByDateRange(
        startDate: Date,
        endDate: Date,
    ): Flow<MomentRecordEntity?> {
        return database.momentRecordDao().getMomentRecordByDateRange(startDate, endDate)
    }

    override suspend fun deleteMomentRecord(momentRecord: MomentRecordEntity) {
        database.momentRecordDao().deleteMomentRecord(momentRecord)
    }

    override suspend fun deleteMomentRecordById(id: Long) {
        database.momentRecordDao().deleteMomentRecordById(id)
    }

    override suspend fun insertMood(mood: MoodEntity) {
        database.moodDao().insertMood(mood)
    }

    override suspend fun updateMood(mood: MoodEntity) {
        database.moodDao().updateMood(mood)
    }

    override fun getAllMoods(): Flow<List<MoodEntity>?> {
        return database.moodDao().getAllMoods()
    }

    override fun getMoodById(id: Long): Flow<MoodEntity?> {
        return database.moodDao().getMoodById(id)
    }

    override suspend fun deleteMood(mood: MoodEntity) {
        database.moodDao().deleteMood(mood)
    }

    override suspend fun deleteMoodById(id: Long) {
        database.moodDao().deleteMoodById(id)
    }

    override suspend fun insertMoodGraphData(moodGraphData: MoodGraphDataEntity) {
        database.moodGraphDataDao().insertMoodGraphData(moodGraphData)
    }

    override suspend fun updateMoodGraphData(moodGraphData: MoodGraphDataEntity) {
        database.moodGraphDataDao().updateMoodGraphData(moodGraphData)
    }

    override fun getAllMoodGraphData(): Flow<List<MoodGraphDataEntity>?> {
        return database.moodGraphDataDao().getAllMoodGraphData()
    }

    override fun getMoodGraphDataById(id: Long): Flow<MoodGraphDataEntity?> {
        return database.moodGraphDataDao().getMoodGraphDataById(id)
    }

    override suspend fun deleteMoodGraphData(moodGraphData: MoodGraphDataEntity) {
        database.moodGraphDataDao().deleteMoodGraphData(moodGraphData)
    }

    override suspend fun deleteMoodGraphDataById(id: Long) {
        database.moodGraphDataDao().deleteMoodGraphDataById(id)
    }

    override suspend fun insertSong(song: SongEntity) {
        database.songDao().insertSong(song)
    }

    override suspend fun updateSong(song: SongEntity) {
        database.songDao().updateSong(song)
    }

    override fun getAllSongs(): Flow<List<SongEntity>?> {
        return database.songDao().getAllSongs()
    }

    override fun getSongById(id: Long): Flow<SongEntity?> {
        return database.songDao().getSongById(id)
    }

    override suspend fun deleteSong(song: SongEntity) {
        database.songDao().deleteSong(song)
    }

    override suspend fun deleteSongById(id: Long) {
        database.songDao().deleteSongById(id)
    }
}