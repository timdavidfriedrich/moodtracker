package de.timdavidfriedrich.moodtracker.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MomentRecordWithEmotionsRelation(
    @Embedded
    val momentRecord: MomentRecordEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "momentRecordId",
    )
    val emotions: List<EmotionEntity>,
)
