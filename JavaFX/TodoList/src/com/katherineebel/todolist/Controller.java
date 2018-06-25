package com.katherineebel.todolist;

import com.katherineebel.todolist.datamodel.Todo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Todo> todos;

    @FXML
    private ListView<Todo> todoListView;
    @FXML
    private TextArea todoDetailsArea;
    @FXML
    private Label deadlineLabel;

    public void initialize() {
        Todo item1 = new Todo("mail birthday card",
                "Buy a 30th birthday car for John",
                LocalDate.of(2018, Month.JULY, 25));
        Todo item2 = new Todo("Doctor's Appointment",
                "See Dr Smith at 123 Main Street",
                LocalDate.of(2018, Month.AUGUST, 10));
        Todo item3 = new Todo("Finish design proposal for client",
                "I promised Mike I'd email website mockups by Friday Sept 30th",
                LocalDate.of(2018, Month.SEPTEMBER, 30));
        Todo item4 = new Todo("Pickup Doug at train station",
                "doug's arriving on June 27 on the 5:00 train",
                LocalDate.of(2018, Month.JUNE, 27));
        Todo item5 = new Todo("Pick up dry cleaning",
                "The clothes should be ready by 2:30",
                LocalDate.of(2018, Month.JUNE, 29));

        todos = new ArrayList<>();
        todos.add(item1);
        todos.add(item2);
        todos.add(item3);
        todos.add(item4);
        todos.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Todo> observable, Todo oldValue, Todo newValue) {
                if (newValue != null) {
                    todoDetailsArea.setText(newValue.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(newValue.getDeadline()));
                }
            }
        });
        todoListView.getItems().setAll(todos);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleListClickListView() {
        Todo item = (Todo) todoListView.getSelectionModel().getSelectedItem();
        todoDetailsArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n").append("Due: ").append(item.getDeadline().toString());
//        todoDetailsArea.setText(sb.toString());
    }
}
