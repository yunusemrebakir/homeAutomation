package com.yunusemrebakir.homecontrol;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Utilities {

    public static List<?> deserialize(String sharedPrefName, Context context, Type type) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefName, MODE_PRIVATE);
        Gson gson = new Gson();

       // Type switchListType = new TypeToken<List<?>>() {}.getType();
        List<?> list = gson.fromJson(sharedPreferences.getString(sharedPrefName, null), type );
        if (list == null) {

            list = new ArrayList<>();
        }

        return list;

    }

    public static void serializeAndSave(List<?> list, String sharedPrefName, Context context){

        SharedPreferences switches = context.getSharedPreferences(sharedPrefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = switches.edit();
        Gson gson = new Gson();
        editor.putString(sharedPrefName, gson.toJson(list));
        editor.commit();

    }

    //TODO switch üzerine long-press olunca edit açılması feature'ı implement edildiğinde bu metodu düzeltip kullan
    public void loadDataForEditing() {
    }
}
