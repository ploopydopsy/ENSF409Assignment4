package edu.ucalgary.oop;

public interface Setting {
    String getCurrentValue();
    void setValue(String category, String value);
    String getCurrentCategory();
}
