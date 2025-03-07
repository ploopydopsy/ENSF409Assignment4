package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class MusicTest {
    private String expectedTitle = "Bohemian Rhapsody";
    private int expectedDuration = 6;
    private String expectedGenre = "Rock";
    private String expectedArtist = "Queen";
    private Music music;

    @Before
    public void setUp() {
        music = new Music(expectedTitle, expectedDuration, expectedGenre, expectedArtist);
    }

    @Test
    public void testMusicToString() {
        String expectedString = "Bohemian Rhapsody (Rock, 6 mins, by Queen)";
        assertEquals("toString() correctly formats music details",
                expectedString, music.toString());
    }

    @Test
    public void testGetArtist() {
        assertEquals("getArtist() returns value set by constructor",
                expectedArtist, music.getArtist());
    }

    @Test
    public void testGetTitleFromParent() {
        assertEquals("getTitle() returns value set by constructor",
                expectedTitle, music.getTitle());
    }

    @Test
    public void testGetDurationFromParent() {
        assertEquals("getDuration() returns value set by constructor",
                expectedDuration, music.getDuration());
    }

    @Test
    public void testGetGenreFromParent() {
        assertEquals("getGenre() returns value set by constructor",
                expectedGenre, music.getGenre());
    }
}

