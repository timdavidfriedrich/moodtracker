package de.timdavidfriedrich.moodtracker.common.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmotionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmotion(emotion: EmotionEntity)

    @Update
    suspend fun updateEmotion(emotion: EmotionEntity)

    @Query("SELECT * FROM emotions")
    fun getAllEmotions(): Flow<List<EmotionEntity>>

    @Query("SELECT * FROM emotions WHERE id = :id")
    fun getEmotionById(id: Long): Flow<EmotionEntity?>

    @Delete
    suspend fun deleteEmotion(emotion: EmotionEntity)

    @Query("DELETE FROM emotions WHERE id = :id")
    suspend fun deleteEmotionById(id: Long)
}