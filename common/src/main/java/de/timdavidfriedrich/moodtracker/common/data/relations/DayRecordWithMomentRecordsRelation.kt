package de.timdavidfriedrich.moodtracker.common.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import de.timdavidfriedrich.moodtracker.common.data.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.entities.MomentRecordEntity

data class DayRecordWithMomentRecordsRelation(
    @Embedded
    val dayRecord: DayRecordEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayRecordId",
    )
    val momentRecords: List<MomentRecordEntity>,
)
