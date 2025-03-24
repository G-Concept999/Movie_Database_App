package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;

import com.example.movie_database_app.model.Movie;
import com.example.movie_database_app.util.ErrorHandler;

/**
 * MovieViewHolder is a ViewHolder class used in a RecyclerView to display movie data.
 * It holds references to the views within a movie item layout, allowing efficient
 * binding of movie data to those views.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder{
    private ImageView posterImageView;
    private TextView titleTextView, yearTextView, genreTextView;

    private Context context;

    public MovieViewHolder(@NonNull View MovieView) {
        super(MovieView);
        // Initialize the context
        posterImageView = MovieView.findViewById(R.id.posterImageView);
        titleTextView = MovieView.findViewById(R.id.titleTextView);
        yearTextView = MovieView.findViewById(R.id.yearTextView);
        genreTextView = MovieView.findViewById(R.id.genreTextView);
    }

    /**
     * Binds the data from a Movie object to the corresponding TextViews in the layout.
     * This method populates the title, year, and genre TextViews with the data retrieved from the
     * provided Movie object.
     *
     * @param movie The Movie object containing the data to be displayed.
     *              Must not be null.
     *
     * @throws NullPointerException if the provided `movie` is null.
     */
    void bind(Movie movie) {
        titleTextView.setText(movie.getTitle());
        yearTextView.setText(movie.getYear().toString());
        genreTextView.setText(movie.getGenre());

        int posterResId = ErrorHandler.getDrawableResourceId(
                context, movie.getPosterResource(), R.drawable.placeholder_image);

        if (posterResId != 0) {
            posterImageView.setImageResource(posterResId);
        } else {
            posterImageView.setImageResource(R.drawable.placeholder_image);
        }
    }
}
