package com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto;

public class TaskDTO {
    private boolean completed;
    private long user;
    private long id;
    private String value;

    public TaskDTO(boolean completed, long user, long id, String value) {
        this.completed = completed;
        this.user = user;
        this.id = id;
        this.value = value;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
