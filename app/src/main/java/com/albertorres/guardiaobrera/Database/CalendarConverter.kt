package com.albertorres.guardiaobrera.Database

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {


    @TypeConverter
    fun toCalendar(value:Long?):Calendar? = if (value== null)null else Calendar.getInstance().apply { timeInMillis=value }

    @TypeConverter
    fun fromCalendar(c:Calendar?):Long?=c?.time?.time


}