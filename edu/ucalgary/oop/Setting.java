package edu.ucalgary.oop;

public interface Setting {
    String getCurrentValue();
    void setValue(String category, String value) throws IllegalArgumentException;
    String getCurrentCategory();
}
