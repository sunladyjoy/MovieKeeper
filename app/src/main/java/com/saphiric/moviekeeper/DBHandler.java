package com.saphiric.moviekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Saphiric on 11/5/14.
 * 
 * This clas will serve as the CRUD for the movie database.
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
    public static final String COLUMN_MOVIEGENRE = "moviegenre"
    
    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    
    // Sets up the creates the movies table in the database passed as a parameter, and its columns
    // are set up using the contants setup above.  This is done during the onCreate method.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " +
             TABLE_MOVIES + "("
             + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_MOVIETITLE 
             + " TEXT," + COLUMN_RELEASEYEAR + " TEXT," + COLUMN_MOVIEGENRE + " TEXT" + ")";
      db.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
	    onCreate(db);
    }
    
    public void addMovie(Movie movie) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIETITLE, movie.getMovieTitle());
        values.put(COLUMN_RELEASEYEAR, movie.getReleaseYear());
        value.put(COLUMN_MOVIEGENRE, movie.getMovieGenre());
 
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.insert(TABLE_Movies, null, values);
        db.close();
}

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

public boolean deleteMovie(String movietitle) {
	
	boolean result = false;
	
	String query = "Select * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_MOVIETITLE + " =  \"" + movietitle + "\"";

	SQLiteDatabase db = this.getWritableDatabase();
	
	Cursor cursor = db.rawQuery(query, null);
	
	Movie movie = new Movie();
	
	if (cursor.moveToFirst()) {
		movie.setID(Integer.parseInt(cursor.getString(0)));
		db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
	            new String[] { String.valueOf(movie.getID()) });
		cursor.close();
		result = true;
	}
	db.close();
	return result;
}

}
