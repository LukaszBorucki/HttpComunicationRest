package com.sdaacademy.borucki.lukasz.httpcomunicationrest.model;

public class Task {
    private boolean completed;
    private Long id;
    private String value;

    public Task(boolean completed, Long id, String value) {
        this.completed = completed;
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
