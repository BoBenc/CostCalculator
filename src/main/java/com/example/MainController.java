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
    Integer selectedId;

    @FXML
    private TextField allCostField;

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
        int totalCost = 0;
        for (Costs costs : costsList) {
            itemsListView.getItems().add(getLine(costs));
            totalCost += costs.getPrice();
        }
        allCostField.setText(totalCost + " Ft");
    }

    protected String getLine(Costs costs) {
        return costs.getId() + " - " +  costs.getName() + " - " + costs.getPrice() + " Ft - " + costs.getCategory() + " - " + costs.getComment();
    }

    protected void setInputs() {
        nameField.setText("");
        priceSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000000, 0)
        );
        categoryCombo.getItems().clear();
        categoryCombo.getItems().addAll("Villany", "Víz", "Gáz", "Internet", "Étel", "Egyéb");
        categoryCombo.getSelectionModel().clearSelection();
        commentArea.setText("");
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        startAdd();
    }

    private int getNextId() {
        int id = 1;
        boolean used;
        do {
            used = false;
            for (Costs cost : costsList) {
                if (cost.getId() == id) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                break;
            }
            id++;
        } while (true);
        return id;
    }

    protected void startAdd() {
        if (nameField.getText().isEmpty() || categoryCombo.getValue() == null || commentArea.getText().isEmpty()) {
            System.err.println("Minden mező kitöltése kötelező!");
            return;
        }
        Costs costs = new Costs();
        costs.setId(getNextId());
        costs.setName(nameField.getText());
        costs.setPrice(priceSpinner.getValue());
        costs.setCategory(categoryCombo.getValue());
        costs.setComment(commentArea.getText());
        costsList.add(costs);
        itemsListView.getItems().add(getLine(costs));
        Storage.writeCosts(costsList);
        setInputs();
        loadCosts();
    }

    void loadSelectedCost() {
        Costs cost = getSelectedCost();
        nameField.setText(cost.getName());
        priceSpinner.getValueFactory().setValue(cost.getPrice());
        categoryCombo.setValue(cost.getCategory());
        commentArea.setText(cost.getComment());
    }

    private Costs getSelectedCost() {
        Costs cost = new Costs();
        for (Costs c : costsList) {
            if (c.getId() == selectedId) {
                cost = c;
            }
        }
        return cost;
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        costsList.removeIf(cost -> cost.getId() == selectedId);
        loadCosts();
        Storage.writeCosts(costsList);
        setInputs();
    }

    @FXML
    void onClickItemsListView(MouseEvent event) {
        String line = itemsListView.getSelectionModel().getSelectedItem();
        String selectedLine = line.split(" - ")[0];
        selectedId = Integer.parseInt(selectedLine);
        loadSelectedCost();
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        startModify();
        setInputs();
    }

    private void startModify() {
        for(Costs cost : costsList) {
            if (cost.getId() == selectedId) {
                cost.setName(nameField.getText());
                cost.setPrice(priceSpinner.getValue());
                cost.setCategory(categoryCombo.getValue());
                cost.setComment(commentArea.getText());
            }
        }
        loadCosts();
    }
}