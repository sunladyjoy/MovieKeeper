package com.saphiric.moviekeeper;

/**
 * Created by Saphiric on 11/3/14.
 */

/**
 * This file will serve as the data model for the database information.  Information will be stored here and transferred between
 * activities and the data handler class
 */
public class Movie {

    private int _id;
    private String _movietitle;
    private String _releaseyear;
    private String _moviegenre;

    public Movie(){

    }

    public Movie(int id, String movietitle, String releaseyear, String moviegenre){
        this._id = id;
        this._movietitle = movietitle;
        this._releaseyear = releaseyear;
        this._moviegenre = moviegenre;
    }

    public Movie(String movietitle, String releaseyear, String moviegenre){
        this._movietitle = movietitle;
        this._releaseyear = releaseyear;
        this._moviegenre = moviegenre;
    }

    /**
     * Getter and setter for id field of the movie
     * @param id should be the integer for incrementing the database
     */
    public void setID(int id) {
        this._id = id;
    }

    public int getId(){
        return this._id;
    }

    /**
     * Getter and setter for the movietitle property
     * @param movietitle should be the name of the movie
     */
    public void setMovieTitle(String movietitle){
        this._movietitle = movietitle;
    }

    public String getMovieTitle(){
        return this._movietitle;
    }

    /**
     * Getter and setter for the release year
     * @param releaseyear should be the release year of the movie
     */
    public void setReleaseYear(String releaseyear){
        this._releaseyear = releaseyear;
    }

    public String getReleaseYear(){
        return this._releaseyear;
    }

    /**
     * Getter and setter for the movie genre
     * @param moviegenre should be the genre of the movie
     */
    public void setMovieGenre(String moviegenre){
        this._moviegenre = moviegenre;
    }

    public String getMovieGenre(){
        return this._moviegenre;
    }
}
