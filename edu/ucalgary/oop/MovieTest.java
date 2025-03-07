package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class MovieTest {
    private String expectedTitle = "Inception";
    private int expectedDuration = 148;
    private String expectedGenre = "Sci-Fi";
    private String expectedDirector = "Christopher Nolan";
    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie(expectedTitle, expectedDuration, expectedGenre,
                expectedDirector);
    }

    @Test
    public void testMovieToString() {
        String expectedString =
                "Inception (Sci-Fi, 148 mins, directed by Christopher Nolan)";
        assertEquals("toString() correctly formats movie",
                expectedString, movie.toString());
    }

    @Test
    public void testGetDirector() {
        assertEquals("getDirector() returns value set by constructor",
                expectedDirector, movie.getDirector());
    }

    @Test
    public void testGetTitleFromParent() {
        assertEquals("getTitle() returns value set by constructor",
                expectedTitle, movie.getTitle());
    }

    @Test
    public void testGetDurationFromParent() {
        assertEquals("getDuration() returns value set by constructor",
                expectedDuration, movie.getDuration());
    }

    @Test
    public void testGetGenreFromParent() {
        assertEquals("getGenre() returns value set by constructor",
                expectedGenre, movie.getGenre());
    }
}

