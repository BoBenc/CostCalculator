package com.example;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {
    ArrayList<Costs> costsList = Storage.readCosts();

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private TextArea commentArea;

    @FXML
    private ListView<String> itemsListView;

    @FXML
    private TextField nameField;

    @FXML
    private Spinner<Integer> priceSpinner;

    @FXML
    void initialize() {
        this.costsList = Storage.readCosts();
        loadCosts();
        setInputs();
    }

    protected void loadCosts() {
        itemsListView.getItems().clear();
        for (Costs costs : costsList) {
            itemsListView.getItems().add(getLine(costs));
        }
    }

    protected String getLine(Costs costs) {
        return costs.getName() + " - " + costs.getPrice() + " Ft - " + costs.getCategory() + " - " + costs.getComment();
    }

    protected void setInputs() {
        nameField.setText("");
        priceSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000000, 0)
        );
        categoryCombo.getItems().addAll("Villany", "Víz", "Gáz", "Internet", "Étel", "Egyéb");
        commentArea.setText("");
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        startAdd();
    }

    protected void startAdd() {
        Costs costs = new Costs();
        costs.setId(costsList.size() + 1);
        costs.setName(nameField.getText());
        costs.setPrice(priceSpinner.getValue());
        costs.setCategory(categoryCombo.getValue());
        costs.setComment(commentArea.getText());
        costsList.add(costs);
        itemsListView.getItems().add(getLine(costs));
        setInputs();
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
