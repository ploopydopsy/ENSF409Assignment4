package edu.ucalgary.oop;

public class Movie extends Content {
    private String director;

    public Movie(String title, int duration, String genre, String director) {
        super(title, duration, genre);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getGenre() + ", " + getDuration() + " mins, directed by " + director + ")";
    }

}
