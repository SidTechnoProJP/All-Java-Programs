package com.bookmyshow;

public class Movie {
    private String name;
    private Language language;
    private Genre genre;

    public Movie(String name, Language language, Genre genre) {
        this.name = name;
        this.language = language;
        this.genre = genre;
    }

	public void setMovieName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public Language getLanguage() {
        return language;
    }

    public Genre getGenre() {
        return genre;
    }

}
