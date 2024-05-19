package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.SongEntity
import de.timdavidfriedrich.moodtracker.common.domain.models.Song

object SongLocalMapper : LocalMapper<SongEntity, Song> {
    override fun toModel(entity: SongEntity?): Song? {
        entity ?: return null
        return Song(
            id = entity.id,
            title = entity.title,
            artist = entity.artist,
            album = entity.album,
            coverUrl = entity.coverUrl,
            url = entity.url,
        )
    }

    override fun toEntity(model: Song?): SongEntity? {
        model ?: return null
        return SongEntity(
            id = model.id,
            title = model.title,
            artist = model.artist,
            album = model.album,
            coverUrl = model.coverUrl,
            url = model.url,
        )
    }
}