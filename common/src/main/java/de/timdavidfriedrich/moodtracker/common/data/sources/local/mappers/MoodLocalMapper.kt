package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodEntity
import de.timdavidfriedrich.moodtracker.common.domain.models.Mood

object MoodLocalMapper : LocalMapper<MoodEntity, Mood> {
    override fun toModel(entity: MoodEntity?): Mood? {
        entity ?: return null
        return Mood(
            id = entity.id,
            score = entity.score,
        )
    }

    override fun toEntity(model: Mood?): MoodEntity? {
        model ?: return null
        return MoodEntity(
            id = model.id,
            score = model.score,
        )
    }
}