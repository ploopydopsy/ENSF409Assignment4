package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class GeneralConfigurationTest {
    private GeneralConfiguration config;
    private String validCategory = "Autoplay";
    private String validValue = "On";
    private String invalidCategory = "InvalidCategory";
    private String invalidValue = "InvalidValue";

    @Before
    public void setUp() throws Exception {
        config = new GeneralConfiguration(validCategory, validValue);
    }

    @Test
    public void testGetCategories() {
        String[] expectedCategories = {"Autoplay", "Notifications"};
        assertArrayEquals("getCategories() returns all valid categories",
            expectedCategories, config.getCategories());
    }

    @Test
    public void testGetOptionsForAutoplay() {
        String[] expectedOptions = {"On", "Off"};
        assertArrayEquals("getOptions() returns valid options for Autoplay",
            expectedOptions, config.getOptions("Autoplay"));
    }

    @Test
    public void testGetOptionsForNotifications() {
        String[] expectedOptions = {"On", "Off"};
        assertArrayEquals("getOptions() returns valid options for Notifications",
            expectedOptions, config.getOptions("Notifications"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOptionsForInvalidCategory() {
        config.getOptions(invalidCategory);
    }

    @Test
    public void testGetCurrentCategoryAfterSet() {
        config.setValue("Notifications", "Off");
        assertEquals("getCurrentCategory() returns the category last set",
            "Notifications", config.getCurrentCategory());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueWithIllegalValue() {
        config.setValue("Autoplay", invalidValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidCategory() throws Exception {
        new GeneralConfiguration(invalidCategory, validValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidValue() throws Exception {
        new GeneralConfiguration(validCategory, invalidValue);
    }
}

