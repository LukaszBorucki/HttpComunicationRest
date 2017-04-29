package com.sdaacademy.borucki.lukasz.httpcomunicationrest.model;

public class Task {
    private boolean completed;
    private long user;
    private String value;

    public Task(boolean completed, long user, String value) {
        this.completed = completed;
        this.user = user;
        this.value = value;
    }

    public Task() {
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
