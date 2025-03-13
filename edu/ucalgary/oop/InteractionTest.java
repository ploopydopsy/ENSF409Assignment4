package edu.ucalgary.oop;

import org.junit.*;
import java.time.LocalDateTime;
import java.time.Duration;
import static org.junit.Assert.*;

public class InteractionTest {
    private static final int ALLOWED_TIME_DIFFERENCE_SECONDS = 2;
    private User user;
    private Movie movie;
    private Interaction interaction;
    @Before
    public void setUp() {
        user = new User("test_user");
        movie = new Movie("Inception", 148, "Sci-Fi", "Christopher Nolan");
        interaction = new Interaction(user, movie);
    }
    @Test
    public void testConstructorInitializesFields() {
        // Arrange
        LocalDateTime beforeCreation = LocalDateTime.now();

        // Act
        Interaction interaction = new Interaction(user, movie);
        LocalDateTime afterCreation = LocalDateTime.now();

        // Assert
        assertEquals("Constructor correctly sets the user", user, interaction.getUser());
        assertEquals("Constructor correctly sets the content", movie, interaction.getContent());
        assertTrue("Constructor sets lastAccessed to a time close to creation",
                isWithinAllowedDifference(beforeCreation, afterCreation, interaction.getLastAccessed()));
        assertEquals("Constructor initializes stoppingPoint to 0", 0, interaction.getStoppingPoint());
    }

    @Test
    public void testSetStoppingPointUpdatesValue() {
        // Arrange
        int expectedStoppingPoint = 120;

        // Act
        interaction.setStoppingPoint(expectedStoppingPoint);

        // Assert
        assertEquals("setStoppingPoint correctly updates the stopping point",
                expectedStoppingPoint, interaction.getStoppingPoint());
    }

    @Test
    public void testSetLastAccessedUpdatesValue() {
        // Arrange
        LocalDateTime beforeUpdate = LocalDateTime.now();

        // Act
        interaction.setLastAccessed();
        LocalDateTime afterUpdate = LocalDateTime.now();

        // Assert
        assertTrue("setLastAccessed updates lastAccessed to a time close to now",
                isWithinAllowedDifference(beforeUpdate, afterUpdate, interaction.getLastAccessed()));
    }

    // Helper method to check if a time is within the allowed difference
    private boolean isWithinAllowedDifference(LocalDateTime start, LocalDateTime end, LocalDateTime target) {
        return (!target.isBefore(start.minusSeconds(ALLOWED_TIME_DIFFERENCE_SECONDS)) &&
                !target.isAfter(end.plusSeconds(ALLOWED_TIME_DIFFERENCE_SECONDS)));
    }
}

