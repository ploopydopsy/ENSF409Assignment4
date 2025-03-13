package edu.ucalgary.oop;

public class Content {
    // data members
    private String title;
    private int duration;
    private String genre;

    public Content(String title, int duration, String genre) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
    }

    public String getGenre(){
        return this.genre;
    }

    public int getDuration(){
        return this.duration;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + duration + " mins)";
    }
}
