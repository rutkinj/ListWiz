package com.jrutkin.listwiz.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class wizDatabaseConverters {
    @TypeConverter
    public static Date fromTimeStamp(Long dateVal){
        return dateVal == null ? null : new Date(dateVal);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }

}
