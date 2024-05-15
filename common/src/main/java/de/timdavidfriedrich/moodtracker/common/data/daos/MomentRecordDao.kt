package de.timdavidfriedrich.moodtracker.common.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.MomentRecordWithEmotionsRelation

@Dao
interface MomentRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMomentRecord(momentRecord: MomentRecordEntity)

    @Update
    suspend fun updateMomentRecord(momentRecord: MomentRecordEntity)

    @Query("SELECT * FROM momentRecords")
    suspend fun getAllMomentRecords(): List<MomentRecordEntity>

    @Transaction
    @Query("SELECT * FROM momentRecords")
    suspend fun getAllMomentRecordsWithEmotions(): List<MomentRecordWithEmotionsRelation>

    @Query("SELECT * FROM momentRecords WHERE id = :id")
    suspend fun getMomentRecordById(id: Long): MomentRecordEntity?

    @Delete
    suspend fun deleteMomentRecord(momentRecord: MomentRecordEntity)

    @Query("DELETE FROM momentRecords WHERE id = :id")
    suspend fun deleteMomentRecordById(id: Long)
}