package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

object MomentRecordLocalMapper : LocalMapper<MomentRecordEntity, Record.Moment> {
    override fun toModel(entity: MomentRecordEntity?): Record.Moment? {
        entity ?: return null
        return Record.Moment(
            id = entity.id,
            date = entity.date,
            emotions = entity.emotions?.mapNotNull { EmotionLocalMapper.toModel(it) } ?: listOf(),
            note = entity.note,
            song = SongLocalMapper.toModel(entity.song),
            mood = MoodLocalMapper.toModel(entity.mood),
        )
    }

    override fun toEntity(model: Record.Moment?): MomentRecordEntity? {
        model ?: return null
        return MomentRecordEntity(
            id = model.id,
            date = model.date,
            note = model.note,
            song = SongLocalMapper.toEntity(model.song),
            mood = MoodLocalMapper.toEntity(model.mood),
            emotions = model.emotions.mapNotNull { EmotionLocalMapper.toEntity(it) },
        )
    }

    fun toEntityWithDayRecordId(
        model: Record.Moment,
        dayRecordId: Long?
    ): MomentRecordEntity {
        return MomentRecordEntity(
            id = model.id,
            date = model.date,
            note = model.note,
            song = SongLocalMapper.toEntity(model.song),
            mood = MoodLocalMapper.toEntity(model.mood),
            dayRecordId = dayRecordId,
            emotions = model.emotions.mapNotNull { EmotionLocalMapper.toEntity(it) },
        )
    }
}