package edu.ucalgary.oop;

import java.util.Arrays;


class GeneralConfiguration implements Setting {
    private static final String[][] CATEGORIES = {
        // Argument [0] is the category, argument [1] is the default setting
        // For example, the category "Autoplay" has a default setting of "On"
        // and the other available setting is "Off"
        {"Autoplay", "On", "Off"},
        {"Notifications", "On", "Off"}
    };
    private String currentCategory;
    private String currentValue;

    public GeneralConfiguration (String category, String value) throws Exception{
        setValue(category, value);
    }

    public static String[] getCategories() {
        // Write your code here
        // Return only the categories, not the values
    }

    public static String[] getOptions(String category) {
        // Write your code here
        // Return only the values, not the categories
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setValue(String category, String option) {
        // Write your code here
    }
}


