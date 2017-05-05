package com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto;

public class TaskDTO {
    private boolean completed;
    private Long user;
    private Long id;
    private String value;

    public TaskDTO() {
    }

    public TaskDTO(boolean completed, Long user, Long id, String value) {
        this.completed = completed;
        this.user = user;
        this.id = id;
        this.value = value;
    }

    public TaskDTO(boolean completed, Long user, String value) {
        this.completed = completed;
        this.user = user;
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

    public void setUser(Long user) {
        this.user = user;
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
