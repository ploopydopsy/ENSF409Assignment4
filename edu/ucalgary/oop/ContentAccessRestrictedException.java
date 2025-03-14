package edu.ucalgary.oop;

public class ContentAccessRestrictedException extends Exception {
    private String message;

    public ContentAccessRestrictedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}