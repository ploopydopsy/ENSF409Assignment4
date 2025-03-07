package edu.ucalgary.oop;

import java.util.Arrays;

public class User {
    private String username;
    private Subscription[] subscriptions = new Subscription[0];
    private Setting[] settings = new Setting[0];

    // Constructors
    public User(String username){
        this.username = username;
        this.subscriptions = new Subscription[0];
    }
    public User (String username, String category, String value) {
        this.username = username;
        this.subscriptions = new Subscription[0];
    }

    public User(String username, Subscription[] subscriptions) {
        this.username = username;
        this.subscriptions = subscriptions;
    }

    // getters

    public Setting[] getSettings() {
        return settings;
    }

    public String getCurrentSetting(String category) {

    }

    public Subscription[] getSubscriptions() {
        return subscriptions;
    }

    public String getUsername() {
        return username;
    }

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

    // setters

    public void setSettings(Setting[] settings) {
        this.settings = settings;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // subscription functions

    public void addSubscription(Subscription subscription) {
        subscriptions = Arrays.copyOf(subscriptions, subscriptions.length + 1);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions = Arrays.copyOf(subscriptions, subscriptions.length - 1);
    }

    public void clearSubscriptions() {
        subscriptions = new Subscription[0];
    }



    //misc
    public String playContent (Content media) {
        //TODO: FIX ME
    }


    public void removeSetting(String category) {

    }

}
