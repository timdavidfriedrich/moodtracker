package de.timdavidfriedrich.moodtracker.common.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.relations.DayRecordWithMomentRecordsRelation
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface DayRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayRecord(dayRecord: DayRecordEntity)

    @Update
    suspend fun updateDayRecord(dayRecord: DayRecordEntity)

    @Query("SELECT * FROM dayRecords")
    fun getAllDayRecords(): Flow<List<DayRecordEntity>>

    @Transaction
    @Query("SELECT * FROM dayRecords")
    fun getAllDayRecordsWithMomentRecords(): Flow<List<DayRecordWithMomentRecordsRelation>>

    @Query("SELECT * FROM dayRecords WHERE id = :id")
    fun getDayRecordById(id: Long): Flow<DayRecordEntity?>

    @Transaction
    @Query("SELECT * FROM dayRecords WHERE id = :id")
    fun getDayRecordWithMomentRecordsById(id: Long): Flow<DayRecordWithMomentRecordsRelation?>

    @Transaction
    @Query("SELECT * FROM dayRecords WHERE date >= :startDate AND date < :endDate")
    fun getDayRecordWithMomentRecordsByDateRange(
        startDate: Date,
        endDate: Date,
    ): Flow<DayRecordWithMomentRecordsRelation?>

    @Delete
    suspend fun deleteDayRecord(dayRecord: DayRecordEntity)

    @Query("DELETE FROM dayRecords WHERE id = :id")
    suspend fun deleteDayRecordById(id: Long)
}