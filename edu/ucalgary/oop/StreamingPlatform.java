package edu.ucalgary.oop;

import javax.swing.text.AbstractDocument;
import java.util.Arrays;

public class StreamingPlatform {
    private final String NAME;
    private Content[] catalog = new Content[0];
    private boolean[] premium = new boolean[0];

    public StreamingPlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Content[] getCatalog() { return catalog; }

    public void addContent ( Content media, Boolean isPremium) {
        // TODO
    }
    public void removeContent (Content media) {
        // TODO
    }

    public String playContent (Content media) {
        // TODO
    }

    public boolean isPremium (Content media) {
        // TODO
    }
    // Your code here
}

