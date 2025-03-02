package edu.ucalgary.oop;

import java.util.Arrays;

public class User {
    private String username;
    private Subscription[] subscriptions = new Subscription[0];
    private Setting[] settings = new Setting[0];  

    // Your code here

    private String getDefaultValueForCategory(String category) throws Exception {
        String[][] allCategories = new String[][] {
            LanguageAndAccessibilityConfiguration.getCategories(),
            AppearanceConfiguration.getCategories(),
            GeneralConfiguration.getCategories()
        };

        for (String[] categories : allCategories) {
            for (String cat : categories) {
                if (cat.equals(category)) {
                    return getDefaultOption(category);
                }
            }
        }
        return null;
    }
}

