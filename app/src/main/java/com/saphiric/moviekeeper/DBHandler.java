package com.saphiric.moviekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Saphiric on 11/5/14.
 * <p/>
 * This class will serve as the CRUD for the movie database.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Sets up constants for the movies database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieDB.db";
    private static final String TABLE_MOVIES = "movies";

    // Sets up constants for movie database columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIETITLE = "movietitle";
    public static final String COLUMN_RELEASEYEAR = "releaseyear";
    public static final String COLUMN_MOVIEGENRE = "moviegenre";

    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Sets up the creates the movies table in the database passed as a parameter, and its columns
     * are setup using the constants above.
     *
     * @param db should be a sqlite database where the table should be created
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " +
                TABLE_MOVIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_MOVIETITLE
                + " TEXT," + COLUMN_RELEASEYEAR + " TEXT," + COLUMN_MOVIEGENRE + " TEXT" + ")";
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    /**
     * Sets up a method for when the database is upgraded
     *
     * @param db         takes the database being upgraded
     * @param oldVersion takes the current version of the database being upgraded
     * @param newVersion takes the version number to replace the old version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    /**
     * Method for adding a movie to the database
     *
     * @param movie Takes an instance of the Movie class
     */
    public void addMovie(Movie movie) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIETITLE, movie.getMovieTitle());
        values.put(COLUMN_RELEASEYEAR, movie.getReleaseYear());
        values.put(COLUMN_MOVIEGENRE, movie.getMovieGenre());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    /**
     * Method for searching for a given movie title in the database.
     *
     * @param movietitle Takes the name of the movie being searched for, taken from the movietitle TextEdit field
     * @return returns the movie data if it is found.
     */
    public Movie findMovie(String movietitle) {
        String query = "Select * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIETITLE + " =  \"" + movietitle + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Movie movie = new Movie();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            movie.setID(Integer.parseInt(cursor.getString(0)));
            movie.setMovieTitle(cursor.getString(1));
            movie.setReleaseYear(cursor.getString(2));
            movie.setMovieGenre(cursor.getString(3));
            cursor.close();
        } else {
            movie = null;
        }
        db.close();
        return movie;
    }

    /**
     * Deletes the movie currently being viewed
     *
     * @param movietitle the title of the movie to be deleted
     * @return The result of the deletion, whether it was successful or not
     */
    public boolean deleteMovie(String movietitle) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIETITLE + " =  \"" + movietitle + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Movie movie = new Movie();

        if (cursor.moveToFirst()) {
            movie.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_MOVIES, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(movie.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}
