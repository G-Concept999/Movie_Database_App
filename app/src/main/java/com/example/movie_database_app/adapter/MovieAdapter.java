package com.example.movie_database_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_database_app.R;
import com.example.movie_database_app.model.Movie;

import java.util.List;

/**
 * MovieAdapter is a RecyclerView.Adapter responsible for displaying a list of Movie objects.
 * It creates and binds MovieViewHolder instances to represent each movie in the list within
 * a RecyclerView.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Creates a new MovieViewHolder.
     * @param parent   The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new MovieViewHolder instance that holds a view for displaying movie information.
     *         It inflates the layout "movie_infos.xml" to build the view.
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_infos, parent, false);
        return new MovieViewHolder(view);
    }

    /**
     * Binds data to the view holder for the specified position.
     * Called by RecyclerView to display the data at the specified position,
     * updating the view holder's contents to reflect the item at the given position.
     * It reuses existing view holders.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.bind(movies.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }
}
