package edu.ucalgary.oop;

public class Subscription {
    private String tier = "Basic";
    private double price;
    private StreamingPlatform platform;
    private Setting[] settings = new Setting[0];

    public Subscription(String tier, double price, StreamingPlatform platform) {
        this.tier = tier;
        this.price = price;
        this.platform = platform;
    }

    public Subscription(double price, StreamingPlatform platform) {
        this.price = price;
        this.platform = platform;
    }

    public String getTier() {
        return this.tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StreamingPlatform getPlatform() {
        return this.platform;
    }

    public void setPlatform(StreamingPlatform platform) {
        this.platform = platform;
    }

    public Setting[] getSettings() {
        return this.settings;
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

    public String playContent(Content media) throws ContentAccessRestrictedException {
        if (tier.equals("Premium") || !platform.isPremium(media)) {
            return media.toString();
        }
        throw new ContentAccessRestrictedException("Access Denied");
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
