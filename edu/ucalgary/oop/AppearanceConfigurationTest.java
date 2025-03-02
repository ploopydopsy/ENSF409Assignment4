package edu.ucalgary.oop;


import org.junit.*;
import static org.junit.Assert.*;

public class AppearanceConfigurationTest {
    private AppearanceConfiguration config;
    private String validCategory = "Theme";
    private String validValue = "Light";
    private String invalidCategory = "InvalidCategory";
    private String invalidValue = "InvalidValue";

    @Before
    public void setUp() throws Exception {
        config = new AppearanceConfiguration(validCategory, validValue);
    }

    @Test
    public void testGetCategories() {
        String[] expectedCategories = {"Theme", "Font Size"};
        assertArrayEquals("getCategories() returns all valid categories",
            expectedCategories, config.getCategories());
    }

    @Test
    public void testGetOptionsForTheme() {
        String[] expectedOptions = {"Light", "Dark"};
        assertArrayEquals("getOptions() returns valid options for Theme",
            expectedOptions, config.getOptions("Theme"));
    }

    @Test
    public void testGetOptionsForFontSize() {
        String[] expectedOptions = {"Small", "Medium", "Large"};
        assertArrayEquals("getOptions() returns valid options for Font Size",
            expectedOptions, config.getOptions("Font Size"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOptionsForInvalidCategory() {
        config.getOptions(invalidCategory);
    }

    @Test
    public void testGetCurrentCategoryAfterSet() {
        config.setValue("Font Size", "Medium");
        assertEquals("getCurrentCategory() returns the category last set",
            "Font Size", config.getCurrentCategory());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueWithIllegalValue() {
        config.setValue("Theme", invalidValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidCategory() throws Exception {
        new AppearanceConfiguration(invalidCategory, validValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidValue() throws Exception {
        new AppearanceConfiguration(validCategory, invalidValue);
    }
}

