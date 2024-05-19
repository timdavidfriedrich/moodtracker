package de.timdavidfriedrich.moodtracker.common.data.sources.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.DayRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity

data class DayRecordWithMomentRecordsRelation(
    @Embedded
    val dayRecord: DayRecordEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayRecordId",
        entity = MomentRecordEntity::class,
    )
    val momentRecords: List<MomentRecordWithEmotionsRelation>,
)
