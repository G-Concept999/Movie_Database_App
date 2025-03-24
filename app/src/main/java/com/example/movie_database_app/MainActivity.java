package com.example.movie_database_app;

import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.model.Movie;
import com.example.movie_database_app.adapter.MovieAdapter;
import com.example.movie_database_app.util.ErrorHandler;
import com.example.movie_database_app.util.JsonUtil;

import com.google.android.material.color.DynamicColors;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // UI components
    private TextView textViewDatabase, textViewDatabaseSlogan;
    private RecyclerView menuRecyclerView;

    // Data
    private MovieAdapter movieAdapter;

    // Constants
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Enable Dynamic Colors
        DynamicColors.applyToActivityIfAvailable(this);

        // Initialize UI components
        initializeViews();

        // Load menu data
        loadMoviesData();
    }

    /**
     * Initialize UI components
     */
    private void initializeViews() {
        textViewDatabase = findViewById(R.id.textViewDatabase);
        textViewDatabaseSlogan = findViewById(R.id.textViewDatabaseSlogan);
        menuRecyclerView = findViewById(R.id.recyclerViewMenu);

        // Set up RecyclerView
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setHasFixedSize(true);
    }

    /**
     * Load menu data from JSON file
     */
    private void loadMoviesData() {
        try {
            // Load movies data from JSON file
            List<Movie> movies = JsonUtil.loadMovies(this);

            // Display database name and slogan
            textViewDatabase.setText("Movie Database");
            textViewDatabaseSlogan.setText("All your movies in one place");

            // Create and set adapter
            MovieAdapter movieAdapter = new MovieAdapter(this, movies);
            menuRecyclerView.setAdapter(movieAdapter);

        } catch (Exception e) {
            Log.e(TAG, "Error loading movies data", e);
            ErrorHandler.handleError(
                    this,
                    e,
                    "Failed to load menu data. Please try again later."
            );
        }
    }
}