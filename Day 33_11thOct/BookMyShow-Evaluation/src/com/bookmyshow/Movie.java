package com.bookmyshow;

public class Movie {
    private String movieName;
    private Language movieLanguage;
    private Genre movieGenre;

    public Movie(String name, Language language, Genre genre) {
        this.movieName = name;
        this.movieLanguage = language;
        this.movieGenre = genre;
    }

	public String getMovieName() {
        return movieName;
    }

    public Language getLanguage() {
        return movieLanguage;
    }

    public Genre getGenre() {
        return movieGenre;
    }

}
