package de.timdavidfriedrich.moodtracker.common.data.sources.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity

@ProvidedTypeConverter
class EmotionConverters {
    @TypeConverter
    fun toEmotionEntity(value: String?): List<EmotionEntity>? {
        return Gson().fromJson<List<EmotionEntity>>(value, EmotionEntity::class.java)
    }

    @TypeConverter
    fun fromEmotionEntity(emotion: List<EmotionEntity>?): String? {
        return Gson().toJson(emotion)
    }
}