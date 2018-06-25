package com.katherineebel.todolist.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static final TodoData instance = new TodoData();
    private static final String filename = "TodoListData.txt";
    private List<Todo> todos;
    private final DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void loadTodos() throws IOException {
        todos = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                Todo todo = new Todo(shortDescription, details, date);
                todos.add(todo);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Todo item : todos) {
                bw.write(String.format("%s\t%s\t%s\t",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter
                        )));
                bw.newLine();
            }
        }
    }

//    public void setTodos(List<Todo> todos) {
//        this.todos = todos;
//    }
}
