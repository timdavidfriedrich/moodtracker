package de.timdavidfriedrich.moodtracker.common.data.sources.local.mappers

interface LocalMapper<Entity, Model> {
    fun toModel(entity: Entity?): Model?
    fun toEntity(model: Model?): Entity?
}