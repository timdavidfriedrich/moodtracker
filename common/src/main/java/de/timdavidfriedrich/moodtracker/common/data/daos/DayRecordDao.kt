package de.timdavidfriedrich.moodtracker.common.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.relations.DayRecordWithMomentRecordsRelation

@Dao
interface DayRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayRecord(dayRecord: DayRecordEntity)

    @Update
    suspend fun updateDayRecord(dayRecord: DayRecordEntity)

    @Query("SELECT * FROM dayRecords")
    suspend fun getAllDayRecords(): List<DayRecordEntity>

    @Transaction
    @Query("SELECT * FROM dayRecords")
    suspend fun getAllDayRecordsWithMomentRecords(): List<DayRecordWithMomentRecordsRelation>

    @Query("SELECT * FROM dayRecords WHERE id = :id")
    suspend fun getDayRecordById(id: Long): DayRecordEntity?

    @Delete
    suspend fun deleteDayRecord(dayRecord: DayRecordEntity)

    @Query("DELETE FROM dayRecords WHERE id = :id")
    suspend fun deleteDayRecordById(id: Long)
}