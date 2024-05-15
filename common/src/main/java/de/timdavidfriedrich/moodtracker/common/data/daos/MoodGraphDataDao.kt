package de.timdavidfriedrich.moodtracker.common.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.entities.MoodGraphDataEntity

@Dao
interface MoodGraphDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Update
    suspend fun updateMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Query("SELECT * FROM moodGraphDataList")
    suspend fun getAllMoodGraphData(): List<MoodGraphDataEntity>?

    @Query("SELECT * FROM moodGraphDataList WHERE id = :id")
    suspend fun getMoodGraphDataById(id: Long): MoodGraphDataEntity?

    @Delete
    suspend fun deleteMoodGraphData(moodGraphData: MoodGraphDataEntity)

    @Query("DELETE FROM moodGraphDataList WHERE id = :id")
    suspend fun deleteMoodGraphDataById(id: Long)
}