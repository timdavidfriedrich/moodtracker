package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MoodGraphDataEntity
import de.timdavidfriedrich.moodtracker.common.domain.models.MoodGraphData

object MoodGraphDataLocalMapper : LocalMapper<MoodGraphDataEntity, MoodGraphData> {
    override fun toModel(entity: MoodGraphDataEntity?): MoodGraphData? {
        entity ?: return null
        return MoodGraphData(
            id = entity.id,
            morningMood = MoodLocalMapper.toModel(entity.morningMood),
            afternoonMood = MoodLocalMapper.toModel(entity.afternoonMood),
            eveningMood = MoodLocalMapper.toModel(entity.eveningMood),
            nightMood = MoodLocalMapper.toModel(entity.nightMood),
        )
    }

    override fun toEntity(model: MoodGraphData?): MoodGraphDataEntity? {
        model ?: return null
        return MoodGraphDataEntity(
            id = model.id,
            morningMood = MoodLocalMapper.toEntity(model.morningMood),
            afternoonMood = MoodLocalMapper.toEntity(model.afternoonMood),
            eveningMood = MoodLocalMapper.toEntity(model.eveningMood),
            nightMood = MoodLocalMapper.toEntity(model.nightMood),
        )
    }
}