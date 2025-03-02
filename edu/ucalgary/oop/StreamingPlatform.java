package edu.ucalgary.oop;

import java.util.Arrays;

public class StreamingPlatform {
    private final String name;
    private Content[] catalog = new Content[0];
    private boolean[] premium = new boolean[0];

    public StreamingPlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
  
    public Content[] getCatalog() { return catalog; }

    // Your code here
}

