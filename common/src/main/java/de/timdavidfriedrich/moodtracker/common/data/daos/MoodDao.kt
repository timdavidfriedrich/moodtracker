package de.timdavidfriedrich.moodtracker.common.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.entities.MoodEntity

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMood(mood: MoodEntity)

    @Update
    suspend fun updateMood(mood: MoodEntity)

    @Query("SELECT * FROM moods")
    suspend fun getAllMoods(): List<MoodEntity>?

    @Query("SELECT * FROM moods WHERE id = :id")
    suspend fun getMoodById(id: Long): MoodEntity?

    @Delete
    suspend fun deleteMood(mood: MoodEntity)

    @Query("DELETE FROM moods WHERE id = :id")
    suspend fun deleteMoodById(id: Long)
}