package edu.ucalgary.oop;

public class Music extends Content {
    private final String artist;

    public Music(String title, int duration, String genre, String artist) {
        super(title, duration, genre);
        this.artist = artist;
    }

    public String getArtist(){
        return this.artist;
    }

    @Override
    public String toString(){
        return getTitle() + " (" + getGenre() + ", " + getDuration() + " mins, by " + artist + ")";
    }
}