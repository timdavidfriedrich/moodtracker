package de.timdavidfriedrich.moodtracker.common.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MomentRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMomentRecord(momentRecord: MomentRecordEntity)

    @Update
    suspend fun updateMomentRecord(momentRecord: MomentRecordEntity)

    @Query("SELECT * FROM momentRecords")
    fun getAllMomentRecords(): Flow<List<MomentRecordEntity>>

    @Query("SELECT * FROM momentRecords WHERE id = :id")
    fun getMomentRecordById(id: Long): Flow<MomentRecordEntity?>

    @Delete
    suspend fun deleteMomentRecord(momentRecord: MomentRecordEntity)

    @Query("DELETE FROM momentRecords WHERE id = :id")
    suspend fun deleteMomentRecordById(id: Long)
}