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
        String[] categories = new String[CATEGORIES.length];
        for (int i = 0; i < CATEGORIES.length; i++) {
            categories[i] = CATEGORIES[i][0];
        }
        return categories;
    }

    public static String[] getOptions(String category) {
        for (int i = 0; i < CATEGORIES.length; i++) {
            if (CATEGORIES[i][0].equals(category)) {
                String[] options = new String[CATEGORIES[i].length - 1];
                for (int j = 1; j < CATEGORIES[i].length; j++) {
                    options[j - 1] = CATEGORIES[i][j];
                }
                return options;
            }
        }
        throw new IllegalArgumentException("Invalid category");
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setValue(String category, String option) {
        String[] options = getOptions(category);
        boolean isValid = false;
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(value)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.currentCategory = category;
        this.currentValue = value;
    }
}


