package com.example.movie_database_app.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.Movie;

public class JsonUtil {
    private static final String TAG = "JsonUtil";
    /**
     * Loads a list of Movie objects from a JSON and parses it into a List of Movie objects.
     *
     * @param context    The application context, used to access resources.
     * @return A List of Movie objects parsed from the JSON file.
     * @throws IOException    If an error occurs while reading the JSON file.
     * @throws JSONException If an error occurs while parsing the JSON content.
     * @throws IllegalArgumentException if context is null or resourceId is invalid
     */
    public static List<Movie> loadMovies(Context context) throws IOException, JSONException {
        List<Movie> movieList = new ArrayList<>();
        String jsonData = readJsonFile(context);

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = getStringOrFallback(jsonObject, "title", "N/A");
                String genre = getStringOrFallback(jsonObject, "genre", "N/A");
                String poster = getStringOrFallback(jsonObject, "poster", "N/A");
                int year = parseYear(jsonObject);

                Movie movie = new Movie(title, year, genre, poster);
                movieList.add(movie);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON", e);
            throw e;
        }

        return movieList;
    }


    // Helper method to read a JSON file from resources
    public static String readJsonFile(Context context) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        try (InputStream inputStream = context.getResources().openRawResource(R.raw.movies);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
            throw e;
        }
        return jsonString.toString();
    }

    // Helper method to get a string from the JSON or use a fallback
    private static String getStringOrFallback(JSONObject jsonObject, String key, String fallback) {
        if (jsonObject.isNull(key)) {
            Log.w(TAG, key + " is null in JSON. Using fallback: " + fallback);
            return fallback;
        }
        return jsonObject.optString(key, fallback);
    }

    // Helper method to parse the year from the JSON
    private static int parseYear(JSONObject jsonObject) {
        try {
            // Parsing the year as a double
            String yearString = getStringOrFallback(jsonObject, "year", "0");
            double yearDouble = Double.parseDouble(yearString);

            // Removing the decimal part to get the integer year
            int year = (int) Math.floor(yearDouble);

            // Checking for invalid years
            if (Math.abs(year) < 1900) {
                Log.w(TAG, "Invalid year detected. Setting year to 0");
                return 0;
            }

            return Math.abs(year);
        } catch (NumberFormatException e) {
            // If the year is a string or something else, set it to 0
            Log.w(TAG, "Year is not a valid number: " + jsonObject.optString("year", "N/A"));
            return 0;
        }
    }

}