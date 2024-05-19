package de.timdavidfriedrich.moodtracker.common.data.sources.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity

data class MomentRecordWithEmotionsRelation(
    @Embedded
    val momentRecord: MomentRecordEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "momentRecordId",
    )
    val emotions: List<EmotionEntity>,
)
