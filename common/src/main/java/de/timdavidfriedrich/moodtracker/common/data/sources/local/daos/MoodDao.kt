package de.timdavidfriedrich.moodtracker.common.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMood(mood: MoodEntity)

    @Update
    suspend fun updateMood(mood: MoodEntity)

    @Query("SELECT * FROM moods")
    fun getAllMoods(): Flow<List<MoodEntity>>

    @Query("SELECT * FROM moods WHERE id = :id")
    fun getMoodById(id: Long): Flow<MoodEntity?>

    @Delete
    suspend fun deleteMood(mood: MoodEntity)

    @Query("DELETE FROM moods WHERE id = :id")
    suspend fun deleteMoodById(id: Long)
}