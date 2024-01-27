package com.curiousapps.keepnote.data

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimeStampToDate(value: Long): Date{
        return Date(value)
    }

    @TypeConverter
    fun fromDateToTimeStamp(date: Date): Long{
        return date.time
    }
}