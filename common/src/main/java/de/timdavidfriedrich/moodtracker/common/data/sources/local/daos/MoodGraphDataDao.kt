package de.timdavidfriedrich.moodtracker.common.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodGraphDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodGraphDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Update
    suspend fun updateMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Query("SELECT * FROM moodGraphDataList")
    fun getAllMoodGraphData(): Flow<List<MoodGraphDataEntity>>

    @Query("SELECT * FROM moodGraphDataList WHERE id = :id")
    fun getMoodGraphDataById(id: Long): Flow<MoodGraphDataEntity?>

    @Delete
    suspend fun deleteMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Query("DELETE FROM moodGraphDataList WHERE id = :id")
    suspend fun deleteMoodGraphDataById(id: Long)
}