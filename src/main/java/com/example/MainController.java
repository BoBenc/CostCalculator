package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private ComboBox<?> categoryCombo;

    @FXML
    private TextArea commentArea;

    @FXML
    private ListView<?> itemsListView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    void onClickAddButton(ActionEvent event) {

    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {

    }

    @FXML
    void onClickItemsListView(MouseEvent event) {

    }

    @FXML
    void onClickModifyButton(ActionEvent event) {

    }

}
