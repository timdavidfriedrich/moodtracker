package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class DayRecordWithMomentRecordsRelation(
    @Embedded
    val dayRecord: DayRecordEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayRecordId",
    )
    val momentRecords: List<MomentRecordEntity>,
)
