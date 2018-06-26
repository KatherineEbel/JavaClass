package com.katherineebel.todolist.datamodel;

import java.time.LocalDate;

public class Todo {
    private final String shortDescription;
    private final String details;
    private final LocalDate deadline;

    public Todo(String shortDescription, String details, LocalDate deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.deadline = deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
