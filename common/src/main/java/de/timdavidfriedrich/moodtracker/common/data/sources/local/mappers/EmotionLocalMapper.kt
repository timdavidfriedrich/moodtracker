package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import de.timdavidfriedrich.moodtracker.common.domain.models.Emotion

object EmotionLocalMapper : LocalMapper<EmotionEntity, Emotion> {
    override fun toModel(entity: EmotionEntity?): Emotion? {
        entity ?: return null
        return Emotion(
            id = entity.id,
            icon = entity.icon,
            name = entity.name,
            //momentRecordId = entity.momentRecordId,
        )
    }

    override fun toEntity(model: Emotion?): EmotionEntity? {
        model ?: return null
        return EmotionEntity(
            id = model.id,
            icon = model.icon,
            name = model.name,
            //momentRecordId = model.momentRecordId,
        )
    }
}