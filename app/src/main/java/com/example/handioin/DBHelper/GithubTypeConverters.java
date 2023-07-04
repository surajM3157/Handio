package com.example.handioin.DBHelper;

import androidx.room.TypeConverter;

import com.example.handioin.ModelClass.productRequestModal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GithubTypeConverters {

    static Gson gson = new Gson();
    @TypeConverter
    public List<productRequestModal> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<productRequestModal>>() {

        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<productRequestModal> someObjects) {
        return gson.toJson(someObjects);
    }
}
