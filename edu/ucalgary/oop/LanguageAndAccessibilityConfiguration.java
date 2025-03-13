package edu.ucalgary.oop;

public class LanguageAndAccessibilityConfiguration implements Setting{
    private final String[][] CATEGORIES = {
            {"Language", "English", "Spanish", "French"},
            {"Captioning", "On", "Off"}
    };
    private String currentCategory;
    private String currentValue;

    // constuctor
    public LanguageAndAccessibilityConfiguration(String currentCategory, String currentValue) {
        setValue(currentCategory, currentValue);
    }

    // getters
    public String[] getCategory() {
        String[] categories = new String[CATEGORIES.length];
        for (int i = 0; i < CATEGORIES.length; i++) {
            categories[i] = CATEGORIES[i][0];
        }
        return categories;
    }
    public String getCurrentValue() {
        return currentValue;
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public String[] getOptions(String category) {
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
    public String[][] getCategories() {
        return CATEGORIES;
    }

    public void setValue(String category, String value) {
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
