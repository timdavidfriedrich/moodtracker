package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.relations.DayRecordWithMomentRecordsRelation
import de.timdavidfriedrich.moodtracker.common.domain.models.Record

object DayRecordLocalMapper : LocalMapper<DayRecordWithMomentRecordsRelation, Record.Day> {
    override fun toModel(entity: DayRecordWithMomentRecordsRelation?): Record.Day? {
        entity ?: return null
        return Record.Day(
            id = entity.dayRecord.id,
            date = entity.dayRecord.date,
            //emotions = entity.dayRecord.emotions,
            note = entity.dayRecord.note,
            song = SongLocalMapper.toModel(entity.dayRecord.song),
            moments = entity.momentRecords.mapNotNull { MomentRecordLocalMapper.toModel(it) },
            moodGraphData = MoodGraphDataLocalMapper.toModel(entity.dayRecord.moodGraphData),
        )
    }

    override fun toEntity(model: Record.Day?): DayRecordWithMomentRecordsRelation? {
        model ?: return null
        TODO("Not yet implemented")
    }
}