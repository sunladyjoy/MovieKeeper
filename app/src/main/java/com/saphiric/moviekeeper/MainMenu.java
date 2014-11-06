package com.saphiric.moviekeeper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainMenu extends ActionBarActivity {

    TextView idView;
    EditText movieTitleBox;
    EditText releaseYearBox;
    EditText movieGenreBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        idView = (TextView) findViewById(R.id.productID);
        movieTitleBox = (EditText) findViewById(R.id.movieTitle);
        releaseYearBox = (EditText) findViewById(R.id.movieReleaseYear);
        movieGenreBox = (EditText) findViewById(R.id.movieGenre);
    }

    public void newMovie (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String title = movieTitleBox.getText();
        String releaseyear = releaseYearBox.getText();
        String moviegenre = moveGenreBox.getText();

        Movie movie = new Movie(title, releaseyear, moviegenre);

        dbHandler.addMovie(movie);
        movieTitleBox.setText("");
        releaseYearBox.setText("");
        movieGenreBox.setText("");
    }

    public void lookupMovie (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Movie movie = dbHandler.findMovie(movieTitleBox.getText());

        if (movie != null) {
            idView.setText(String.valueOf(movie.getID()));
            releaseYearBox.setText(String.valueOf(movie.getReleaseyear()));
            movieGenreBox.setText(String.valueOf(movie.getMovieGenre()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeMovie (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        boolean result = dbHandler.deleteMovie(movieTitleBox.getText());

        if (result)
        {
            idView.setText("Record Deleted");
            movieTitleBox.setText("");
            releaseYearBox.setText("");
            movieGenreBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
.
.
.
}
