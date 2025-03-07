package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class SubscriptionTest {
    private Subscription subscription;
    private StreamingPlatform platform;
    private Movie premiumMovie;
    private Music freeMusic;

    @Before
    public void setUp() throws Exception {
        platform = new StreamingPlatform("Netflix");
        premiumMovie = new Movie("Inception", 148, "Sci-Fi", "Christopher Nolan");
        freeMusic = new Music("Bohemian Rhapsody", 6, "Rock", "Queen");

        platform.addContent(premiumMovie, true);
        platform.addContent(freeMusic, false);

        subscription = new Subscription("Basic", 9.99, platform);
    }

    @Test
    public void testThreeArgumentConstructor() throws Exception {
        var newSubscription = new Subscription("Premium", 6.07, platform);
        assertEquals("Constructor correctly sets tier", "Premium",
                newSubscription.getTier());
        assertEquals("Constructor correctly sets price", 6.07,
                newSubscription.getPrice(), 0.00);
        assertEquals("Constructor correctly sets platform", platform,
                newSubscription.getPlatform());
    }

    @Test
    public void testTwoArgumentConstructor() throws Exception {
        var newSubscription = new Subscription(6.54, platform);
        assertEquals("Constructor correctly sets tier", "Basic",
                newSubscription.getTier());
        assertEquals("Constructor correctly sets price", 6.54,
                newSubscription.getPrice(), 0.00);
        assertEquals("Constructor correctly sets platform", platform,
                newSubscription.getPlatform());
    }

    @Test
    public void testSetSettingUpdatesSettingValue() throws Exception {
        subscription.setSetting("Language", "Spanish");
        assertEquals("setSetting updates the current value for a category",
                "Spanish", subscription.getCurrentSetting("Language"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSettingThrowsForInvalidCategory() throws Exception {
        subscription.setSetting("InvalidCategory", "Value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSettingThrowsForInvalidValue() throws Exception {
        subscription.setSetting("Language", "InvalidValue");
    }

    @Test
    public void testRemoveSettingResetsToDefault() throws Exception {
        subscription.setSetting("Language", "Spanish");
        subscription.removeSetting("Language");
        assertEquals("removeSetting resets the category to its default value",
                "English", subscription.getCurrentSetting("Language"));
    }

    @Test
    public void testPlayContentSucceedsForNonPremiumContentBasicSubscription() throws Exception{
        String result = subscription.playContent(freeMusic);
        assertEquals("playContent succeeds for non-premium content",
                "Bohemian Rhapsody (Rock, 6 mins, by Queen)", result);
    }

    @Test
    public void testPlayContentSucceedsForNonPremiumContentPremiumSubscription() throws Exception {
        subscription.setTier("Premium");
        String result = subscription.playContent(freeMusic);
        assertEquals("playContent succeeds for non-premium content",
                "Bohemian Rhapsody (Rock, 6 mins, by Queen)", result);
    }

    @Test(expected = ContentAccessRestrictedException.class)
    public void testPlayContentThrowsForPremiumContentWithBasicTier() throws Exception {
        subscription.playContent(premiumMovie);
    }

    @Test
    public void testPlayContentSucceedsForPremiumContentWithPremiumTier() throws Exception {
        subscription.setTier("Premium");
        String result = subscription.playContent(premiumMovie);
        assertEquals("playContent succeeds for premium content with Premium tier",
                "Inception (Sci-Fi, 148 mins, directed by Christopher Nolan)", result);
    }

    @Test
    // The method getCurrentSettings() should return the default setting
    // if the setting is not set for the subscription.
    public void testDefaultSettingsIfNotSet() throws Exception {
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "English",
                subscription.getCurrentSetting("Language"));
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "On",
                subscription.getCurrentSetting("Captioning"));
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "Light",
                subscription.getCurrentSetting("Theme"));
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "Small",
                subscription.getCurrentSetting("Font Size"));
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "On",
                subscription.getCurrentSetting("Autoplay"));
        assertEquals("getCurrentSettings should return the default if setting " +
                        "was not set for the subscription", "On",
                subscription.getCurrentSetting("Notifications"));
    }
}

