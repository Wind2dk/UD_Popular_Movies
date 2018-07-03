package dk.getonboard.android.popularmovies.database;

import android.arch.persistence.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntArrayConverter {

    @TypeConverter
    public static int[] toIntArray(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            int[] intArray = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                intArray[i] = jsonArray.optInt(i);
            }
            return intArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    @TypeConverter
    public static String toJson(int[] intArray) {
        JSONArray jsonArray = new JSONArray();
        for(int someInt : intArray) {
            jsonArray.put(someInt);
        }
        return jsonArray.toString();
    }
}
