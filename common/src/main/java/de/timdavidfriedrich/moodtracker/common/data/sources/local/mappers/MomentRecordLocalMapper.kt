package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.MomentRecordEntity
import de.timdavidfriedrich.moodtracker.common.data.sources.local.relations.MomentRecordWithEmotionsRelation
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

object MomentRecordLocalMapper : LocalMapper<MomentRecordWithEmotionsRelation, Record.Moment> {
    override fun toModel(entity: MomentRecordWithEmotionsRelation?): Record.Moment? {
        entity ?: return null
        return Record.Moment(
            id = entity.momentRecord.id,
            date = entity.momentRecord.date,
            emotions = entity.emotions.mapNotNull { EmotionLocalMapper.toModel(it) },
            note = entity.momentRecord.note,
            song = SongLocalMapper.toModel(entity.momentRecord.song),
            mood = MoodLocalMapper.toModel(entity.momentRecord.mood),
        )
    }

    override fun toEntity(model: Record.Moment?): MomentRecordWithEmotionsRelation? {
        model ?: return null
        return MomentRecordWithEmotionsRelation(
            momentRecord = MomentRecordEntity(
                id = model.id,
                date = model.date,
                note = model.note,
                song = SongLocalMapper.toEntity(model.song),
                mood = MoodLocalMapper.toEntity(model.mood),
            ),
            emotions = model.emotions.mapNotNull { EmotionLocalMapper.toEntity(it) },
        )
    }

    fun toEntityWithDayRecordId(
        model: Record.Moment,
        dayRecordId: Long?
    ): MomentRecordWithEmotionsRelation {
        return MomentRecordWithEmotionsRelation(
            momentRecord = MomentRecordEntity(
                id = model.id,
                date = model.date,
                note = model.note,
                song = SongLocalMapper.toEntity(model.song),
                mood = MoodLocalMapper.toEntity(model.mood),
                dayRecordId = dayRecordId,
            ),
            emotions = model.emotions.mapNotNull { EmotionLocalMapper.toEntity(it) },
        )
    }
}