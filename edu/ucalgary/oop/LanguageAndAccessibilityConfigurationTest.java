package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class LanguageAndAccessibilityConfigurationTest {
    private LanguageAndAccessibilityConfiguration config;
    private String validCategory = "Language";
    private String validValue = "English";
    private String invalidCategory = "InvalidCategory";
    private String invalidValue = "InvalidValue";

    @Before
    public void setUp() throws Exception {
        config = new LanguageAndAccessibilityConfiguration(validCategory, validValue);
    }

    @Test
    public void testGetCategories() {
        String[] expectedCategories = {"Language", "Captioning"};
        assertArrayEquals("getCategories() returns all valid categories",
                expectedCategories, config.getCategories());
    }

    @Test
    public void testGetOptionsForLanguage() {
        String[] expectedOptions = {"English", "Spanish", "French"};
        assertArrayEquals("getOptions() returns valid options for Language",
                expectedOptions, config.getOptions("Language"));
    }

    @Test
    public void testGetOptionsForCaptioning() {
        String[] expectedOptions = {"On", "Off"};
        assertArrayEquals("getOptions() returns valid options for Captioning",
                expectedOptions, config.getOptions("Captioning"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOptionsForInvalidCategory() {
        config.getOptions(invalidCategory);
    }

    @Test
    public void testGetCurrentCategoryAfterSet() {
        config.setValue("Captioning", "On");
        assertEquals("getCurrentCategory() returns the category last set",
                "Captioning", config.getCurrentCategory());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueWithIllegalValue() {
        config.setValue("Language", invalidValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidCategory() throws Exception {
        new LanguageAndAccessibilityConfiguration(invalidCategory, validValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidValue() throws Exception {
        new LanguageAndAccessibilityConfiguration(validCategory, invalidValue);
    }
}

