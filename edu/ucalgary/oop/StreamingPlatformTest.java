package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class StreamingPlatformTest {
    private StreamingPlatform platform;
    private Movie premiumMovie;
    private Music freeMusic;
    private Movie notAdded;

    @Before
    public void setUp() {
        platform = new StreamingPlatform("Netflix");
        premiumMovie = new Movie("Inception", 148, "Sci-Fi", "Christopher Nolan");
        freeMusic = new Music("Bohemian Rhapsody", 6, "Rock", "Queen");
        platform.addContent(premiumMovie, true);
        platform.addContent(freeMusic, false);
        notAdded = new Movie("Mufasa: The Lion King", 118, "Family", "Barry Jenkins");
    }

    @Test
    public void testAddContent() {
        assertTrue("isPremium() returns true for content marked as premium",
                platform.isPremium(premiumMovie));
    }

    @Test
    public void testIsPremiumForNonPremiumContent() {
        assertFalse("isPremium() returns false for content not marked as premium",
                platform.isPremium(freeMusic));
    }

    @Test
    public void testIsPremiumForUnknownContent() {
        assertFalse("isPremium() returns false for content not in the catalog",
                platform.isPremium(notAdded));
    }

    @Test
    public void testPlayContent() throws Exception {
        platform.addContent(freeMusic, false);
        String expectedOutput = "Bohemian Rhapsody (Rock, 6 mins, by Queen)";
        assertEquals("playContent() returns the toString() of the content",
                expectedOutput, platform.playContent(freeMusic));
    }

    @Test
    public void testGetName() {
        assertEquals("getName() returns the name of the platform",
                "Netflix", platform.getName());
    }

    @Test(expected = ContentAccessRestrictedException.class)
    public void testPlayContentThrowsExceptionForUnknownContent() throws Exception {
        platform.playContent(notAdded); // Content not in the catalog
    }

    @Test
    public void testRemoveContentNotInCatalog() {
        Content[] catalogBefore = platform.getCatalog();

        boolean exceptionThrown = false;
        try {
            platform.removeContent(notAdded);
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        assertFalse("No exception should be thrown trying to remove content " +
                "which wasn't in catalog", exceptionThrown);

        Content[] catalogAfter = platform.getCatalog();

        assertEquals("Catalog should be same length before and after attempt " +
                        "to remove item not in catalog",
                catalogBefore.length, catalogAfter.length);

        for (int i = 0; i < catalogBefore.length; i++) {
            assertEquals(
                    "Catalog should be unchanged when trying to remove item not " +
                            "in catalog", catalogBefore[i], catalogAfter[i]);
        }
    }

    @Test
    public void testRemoveContent() {
        var movieToRemove = new Movie("Bleu", 94, "Drama", "Krzysztof Kieślowski");
        var remainingMusic = new Music("Good Luck, Babe!", 3, "Pop",
                "Chappell Roan");

        platform.addContent(movieToRemove, true); // premium
        platform.addContent(remainingMusic, false); // not premium

        platform.removeContent(movieToRemove);

        Content[] catalog = platform.getCatalog();

        boolean removedFound = false;
        boolean keptFound = false;
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i].equals(movieToRemove)) {
                removedFound = true;
            }
            if (catalog[i].equals(remainingMusic)) {
                keptFound = true;
            }
        }

        assertFalse("Removed content should no longer be in the catalog",
                removedFound);
        assertTrue("Retained content should be in the catalog", keptFound);
        // Check that premium array was also modified; this will fail if 
        // movie premium status was retained when the movie was removed
        assertFalse("Retained content should have same restrictions",
                platform.isPremium(remainingMusic)); // not premium
    }

}

