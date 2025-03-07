package edu.ucalgary.oop;

import java.util.Arrays;

public class Subscription {
    private String tier = "Basic";
    private double price;
    private StreamingPlatform platform;
    private Setting[] settings = new Setting[0];

    public Subscription(String tier, double price, StreamingPlatform platform) {
        // Your code here
    }

    public Subscription(double price, StreamingPlatform platform) {
        this.price = price;
        this.platform = platform;
    }

    public String getTier() {
        return this.tier;
    }

    public double getPrice() {
        return this.price;
    }

    public StreamingPlatform getPlatform() {
        return this.platform;
    }

    public Setting[] getSettings() {
        return Arrays.copyOf(settings, settings.length);
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPlatform(StreamingPlatform platform) {
        this.platform = platform;
    }

    public String getCurrentSetting(String category) throws Exception {
        // Your code here
    }

    public void setSetting(String category, String value) throws Exception {
        // Your code here
    }

    public void removeSetting(String category) {
        // Your code here
    }

    public String playContent(Content content) {
        // Your code here
    }
}

