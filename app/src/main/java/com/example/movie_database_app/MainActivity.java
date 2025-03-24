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
    private RecyclerView movieRecyclerView;


    private List<Movie> movies;

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

        setUpRecyclerView();

        // Load menu data
        loadMoviesData();

        // Display database name and slogan
        textViewDatabase.setText("Movie Database");
        textViewDatabaseSlogan.setText("All your movies in one place");
    }

    /**
     * Initialize UI components
     */
    private void initializeViews() {
        textViewDatabase = findViewById(R.id.textViewDatabase);
        textViewDatabaseSlogan = findViewById(R.id.textViewDatabaseSlogan);


    }

    /**
     * Load menu data from JSON file
     */
    private void loadMoviesData() {
        try {
            // Load movies data from JSON file
            List<Movie> movies = JsonUtil.loadMovies(this);



        } catch (Exception e) {
            Log.e(TAG, "Error loading movies data", e);
            ErrorHandler.handleError(
                    this,
                    e,
                    "Failed to load menu data. Please try again later."
            );
        }
    }

    private void setUpRecyclerView() {
        // Set up RecyclerView
        movieRecyclerView = findViewById(R.id.recyclerViewMovies);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setHasFixedSize(true);

        // Create and set adapter
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        movieRecyclerView.setAdapter(movieAdapter);
    }
}