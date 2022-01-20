package com.example.mvvmhiltdemo.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
   /* @TypeConverter
    fun listToString(genre_ids:List<Int>):String{
     return Gson().toJson(genre_ids)
    }
    @TypeConverter
    fun StringToList(genre_ids:List<Int>):List<Int>
    =Gson().fromJson(genre_ids)
*/
    @TypeConverter
    fun restoreList(listOfString: String?): List<Int?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<Int?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<Int?>?): String? {
        return Gson().toJson(listOfString)
    }
}