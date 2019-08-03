package mohammad.financialsreport.models;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Date;

public class Converters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        gson.toJson(value);
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        gson.fromJson(String.valueOf(date), Date.class);
        return date == null ? null : date.getTime();
    }
}
