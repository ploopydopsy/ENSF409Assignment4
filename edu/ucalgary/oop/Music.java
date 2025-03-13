package edu.ucalgary.oop;

public class Music extends Content {
    private final String artist;

    // constructor
    public Music(String title, int duration, String artist){
        super(title, duration, artist);
    }

    // getter
    public String getArtist(){
        return this.artist;
    }

    // to string
    @Override
    public String toString(){
        return getTitle() + " (" + getGenre() + ", " + getDuration() + " mins, by " + artist + ")";// TODO not sure
    }


}
