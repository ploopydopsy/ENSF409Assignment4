package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private Subscription subscription1;
    private Subscription subscription2;
    private StreamingPlatform netflix;
    private StreamingPlatform spotify;
    private Movie premiumMovie;
    private Music freeMusic;

    @Before
    public void setUp() throws Exception {
        netflix = new StreamingPlatform("Netflix");
        spotify = new StreamingPlatform("Spotify");

        premiumMovie = new Movie("Inception", 148, "Sci-Fi", "Christopher Nolan");
        freeMusic = new Music("Bohemian Rhapsody", 6, "Rock", "Queen");

        netflix.addContent(premiumMovie, true);
        spotify.addContent(freeMusic, false);

        subscription1 = new Subscription("Basic", 9.99, netflix);
        subscription2 = new Subscription("Premium", 14.99, spotify);

        user = new User("test_user");
    }

    @Test
    // The method getCurrentSettings() should return the default setting
    // if the setting is not set for the user.
    public void testDefaultSettingsIfNotSet() throws Exception {
        assertEquals("getCurrentSettings should return the default if setting " +
            "was not set for the user", "English", user.getCurrentSetting("Language"));
        assertEquals("getCurrentSettings should return the default if setting " +
            "was not set for the user", "On", user.getCurrentSetting("Captioning"));
        assertEquals("getCurrentSettings should return the default if setting " +
            "was not set for the user", "Light", user.getCurrentSetting("Theme"));
        assertEquals("getCurrentSettings should return the default if setting " +
            "was not set for the user", "Small", user.getCurrentSetting("Font Size"));
        assertEquals("getCurrentSettings should return the default if setting " +
            "was not set for the user", "On", user.getCurrentSetting("Autoplay"));
        assertEquals("getCurrentSettings should return the default if setting " + 
            "was not set for the user", "On", user.getCurrentSetting("Notifications"));
    }


    @Test
    public void testOneArgumentConstructor() throws Exception {
        assertEquals("Constructor initializes the username correctly",
            "test_user", user.getUsername());
    }

    @Test
    public void testTwoArgumentConstructor() throws Exception {
        String expectedUsername = "january";
        StreamingPlatform prime = new StreamingPlatform("Amazon Prime");
        StreamingPlatform crave = new StreamingPlatform("Crave");
        Subscription subscription1 = new Subscription("Basic", 4.99, crave);
        Subscription subscription2 = new Subscription("Premium", 19.99, prime);
        Subscription[] subscriptions = {subscription1, subscription2};

        User user = new User(expectedUsername, subscriptions);

        assertEquals("Constructor initializes the username correctly",
            expectedUsername, user.getUsername());
        assertArrayEquals("Constructor initializes the subscriptions correctly",
            subscriptions, user.getSubscriptions());
    }

    @Test
    public void testThreeArgumentConstructor() throws Exception {
        String expectedUsername = "tinku";
        String providedCategory = "Language";
        String providedValue = "French";
        User newUser = new User(expectedUsername, providedCategory, providedValue);

        assertEquals("Constructor initializes the username correctly",
            expectedUsername, newUser.getUsername());
        assertEquals("Provided language setting overrides default",
            providedValue, newUser.getCurrentSetting(providedCategory));
    }

    @Test
    public void testSetUsername() {
        user.setUsername("new_user");
        assertEquals("setUsername updates the username correctly",
            "new_user", user.getUsername());
    }

    @Test
    public void testSetSettingUpdatesValue() throws Exception {
        user.setSetting("Language", "Spanish");
        assertEquals("setSetting updates the value of a category",
            "Spanish", user.getCurrentSetting("Language"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSettingThrowsForInvalidCategory() throws Exception {
        user.setSetting("InvalidCategory", "Value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSettingThrowsForInvalidValue() throws Exception {
        user.setSetting("Language", "InvalidValue");
    }

    @Test
    public void testRemoveSettingResetsToDefault() throws Exception {
        user.setSetting("Language", "Spanish");
        user.removeSetting("Language");
        assertEquals("removeSetting resets the category to its default value",
            "English", user.getCurrentSetting("Language"));
    }

    @Test
    public void testAddSubscription() {
        user.addSubscription(subscription1);
        assertEquals("addSubscription adds a subscription to the user",
            1, user.getSubscriptions().length);
        assertEquals("The added subscription is correct",
            subscription1, user.getSubscriptions()[0]);
    }

    @Test
    public void testRemoveSubscription() {
        user.addSubscription(subscription1);
        user.addSubscription(subscription2);
        user.removeSubscription(subscription1);
        assertEquals("removeSubscription removes the specified subscription",
            1, user.getSubscriptions().length);
        assertEquals("The remaining subscription is correct",
            subscription2, user.getSubscriptions()[0]);
    }

    @Test
    public void testClearSubscriptions() {
        user.addSubscription(subscription1);
        user.addSubscription(subscription2);
        user.clearSubscriptions();
        assertEquals("clearSubscriptions removes all subscriptions",
            0, user.getSubscriptions().length);
    }

    @Test
    public void testPlayContentAvailable() {
        user.addSubscription(subscription2); // Premium subscription
        String result = user.playContent(freeMusic);
        assertEquals("playContent plays available content",
            "Bohemian Rhapsody (Rock, 6 mins, by Queen)", result);
    }

    @Test
    public void testPlayContentContentUnavailable() {
        user.addSubscription(subscription1); // Basic subscription
        String result = user.playContent(premiumMovie);
        assertEquals("playContent returns generic unavailable message to user",
            "This content is not available.", result);
    }
}

