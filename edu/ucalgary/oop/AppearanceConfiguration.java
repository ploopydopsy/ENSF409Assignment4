package edu.ucalgary.oop;

public class AppearanceConfiguration implements Setting {
    // data members
    private final String[][] CATEGORIES = {
            {"Theme", "Light", "Dark"},
            {"Font Size", "Small", "Medium", "Large"}
    };
    private String currentCategory;
    private String currentValue;

    // constructor
    public AppearanceConfiguration(String category, String value) {
        setValue(category, value);
    }

    // setters and getters
    public String[] getCategories() {
        String[] categories = new String[CATEGORIES.length];
        for (int i = 0; i < CATEGORIES.length; i++) {
            categories[i] = CATEGORIES[i][0];
        }
        return categories;
    }

    public String getCurrentValue() {
        return currentValue;
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

    public String getCurrentCategory() {
        return currentCategory;
    }

}
