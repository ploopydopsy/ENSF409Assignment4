package edu.ucalgary.oop;

import java.time.LocalDateTime;
class Interaction {
    // data members
    private final User USER;
    private final Content CONTENT;
    private LocalDateTime lastAccessed;
    private int stoppingPoint;

    // getters and setters
    public User getUser() { return this.USER; }

    public Content getContent() { return this.CONTENT; }

    public void setStoppingPoint(int stoppingPoint) {
        this.stoppingPoint = stoppingPoint;
    }

    public int getStoppingPoint() {
        return stoppingPoint;
    }

    public void setLastAccessed() {
        this.lastAccessed = LocalDateTime.now();
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public Interaction(User user, Content content) {
        this.USER = user;
        this.CONTENT = content;
        setLastAccessed();
    }

}

