package de.timdavidfriedrich.moodtracker.common.data.sources.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.timdavidfriedrich.moodtracker.common.data.sources.local.entities.EmotionEntity
import java.lang.reflect.Type


@ProvidedTypeConverter
class EmotionConverters {
    private val collectionType: Type = object : TypeToken<Collection<EmotionEntity>?>() {}.type

    @TypeConverter
    fun toEmotionEntity(value: String?): List<EmotionEntity>? {
        return Gson().fromJson(value, collectionType)
    }

    @TypeConverter
    fun fromEmotionEntity(emotion: List<EmotionEntity>?): String? {
        return Gson().toJson(emotion, collectionType)
    }
}