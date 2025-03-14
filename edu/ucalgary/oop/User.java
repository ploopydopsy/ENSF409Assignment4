package edu.ucalgary.oop;

public class User {
    // data members
    private String username;
    private Setting[] settings = new Setting[0];
    private Subscription[] subscriptions = new Subscription[0];

    public User(String username) {
        this.username = username;
    }

    public User(String username, String category, String value) {
        this.username = username;
        setSetting(category, value);
    }

    public User(String username, Subscription[] subscriptions) {
        this.username = username;
        this.subscriptions = subscriptions;
    }

    public Setting[] getSettings() {
        return settings;
    }

    public String getCurrentSetting(String category) throws IllegalArgumentException {
        for (int i = 0; i < settings.length; i++) {
            if (settings[i].getCurrentCategory().equals(category)) {
                return settings[i].getCurrentValue();
            }
        }
        try {
            return GeneralConfiguration.getOptions(category)[0];
        } catch (IllegalArgumentException e1) {
            try {
                return AppearanceConfiguration.getOptions(category)[0];
            } catch (IllegalArgumentException e2) {
                return LanguageAndAccessibilityConfiguration.getOptions(category)[0];
            }
        }
    }

    public void setSetting(String category, String value) throws IllegalArgumentException {
        for (int i = 0; i < settings.length; i++) {
            if (settings[i].getCurrentCategory().equals(category)) {
                settings[i].setValue(category, value);
                return;
            }
        }

        Setting newSetting;
        try {
            newSetting = new LanguageAndAccessibilityConfiguration(category, value);
        } catch (Exception e1) {
            try {
                newSetting = new AppearanceConfiguration(category, value);
            } catch (Exception e2) {
                newSetting = new GeneralConfiguration(category, value);
            }
        }

        Setting[] newSettings = new Setting[settings.length + 1];
        for (int i = 0; i < settings.length; i++) {
            newSettings[i] = settings[i];
        }
        newSettings[newSettings.length - 1] = newSetting;
        settings = newSettings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addSubscription(Subscription subscription) {
        Subscription[] newSubscriptions = new Subscription[subscriptions.length + 1];
        System.arraycopy(subscriptions, 0, newSubscriptions, 0, subscriptions.length);
        newSubscriptions[newSubscriptions.length - 1] = subscription;
        subscriptions = newSubscriptions;
    }

    public void removeSubscription(Subscription subscription) {
        int count = 0;
        for (int i = 0; i < subscriptions.length; i++) {
            if (subscriptions[i] != subscription) {
                count++;
            }
        }

        Subscription[] newSubscriptions = new Subscription[count];
        int index = 0;
        for (int i = 0; i < subscriptions.length; i++) {
            if (subscriptions[i] != subscription) {
                newSubscriptions[index] = subscriptions[i];
                index++;
            }
        }
        subscriptions = newSubscriptions;
    }

    public void clearSubscriptions() {
        subscriptions = new Subscription[0];
    }

    public Subscription[] getSubscriptions() {
        return subscriptions;
    }

    public String playContent(Content media) {
        for (Subscription subscription : subscriptions) {
            try {
                return subscription.playContent(media);
            } catch (ContentAccessRestrictedException e) {
                continue;
            }
        }
        return "This content is not available.";
    }

    public void removeSetting(String category) {
        int index = -1;
        for (int i = 0; i < settings.length; i++) {
            if (settings[i].getCurrentCategory().equals(category)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }

        Setting[] newSettings = new Setting[settings.length - 1];
        int j = 0;
        for (int i = 0; i < settings.length; i++) {
            if (i != index) {
                newSettings[j] = settings[i];
                j++;
            }
        }
        settings = newSettings;

        try {
            setSetting(category, GeneralConfiguration.getOptions(category)[0]);
        } catch (IllegalArgumentException e1) {
            try {
                setSetting(category, AppearanceConfiguration.getOptions(category)[0]);
            } catch (IllegalArgumentException e2) {
                setSetting(category, LanguageAndAccessibilityConfiguration.getOptions(category)[0]);
            }
        }
    }

}
