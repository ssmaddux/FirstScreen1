package maddux.firstscreen.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import maddux.firstscreen.model.Inventory;
import maddux.firstscreen.model.Part;
import maddux.firstscreen.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that provides control logic for the add product screen of the application.
 * @author Sage Maddux
 */

public class AddProductFormController implements Initializable {






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }





    /**
     * List containing parts associated with the product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Associated parts table view
     */
    @FXML
    private TableView<Part> associatedPartTableView;

    /**
     * Part ID Column for associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    /**
     * Part Name Column for asspciated parts table.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    /**
     * Inventory column for the associated parts table
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;

    /**
     * Price column for assoc parts table
     */
    @FXML
    private  TableColumn<Part, Double> associatedPartPriceColumn;

    /**
     *  All parts Table View.
     */
    @FXML
    private TableView <Part> partTableView;

    /**
     *  Part ID column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     *  Name column for all parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * Inventory level column for the all parts table.
     */
    @FXML
    private  TableColumn<Part, Double> partInventoryColumn;

    /**
     * price column for the all parts table.
     */
    @FXML
    private TableColumn<Part,Double> partPriceColumn;

    /**
     * The part seatrch text field
     */
    @FXML
    private TextField partSearchTextField;

    /**
     * Product Id text Field
     */
    @FXML
    private TextField productIdTextField;

    /**
     * product name text field.
     */
    @FXML
    private  TextField productNameTextField;

    /**
     * product inventory level text field.
     */
    @FXML
    private TextField productInventoryTextField;

    /**
     * product price text field.
     */
    @FXML
    private TextField productPriceTextField;

    /**
     *  product max level text Field.
     */
    @FXML
    private TextField productMaxTextField;

    /**
     * product min level text field.
     */
    @FXML
    private  TextField productMinTextField;


    /**
     * Adds part object selected in the all parts table to the associated parts table.
     * Displays error message if no part is selected.
     *
     * @param event Add button action.
     */

    public void onAddButton(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            associatedParts.add(selectedPart);
            associatedPartTableView.setItems(associatedParts);
        }
    }


    /**
     * Displays confirmation dialog and loads MainScreenController.
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */

    @FXML
    void onCancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }


    /**
     * Loads MainScreenController.
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */

    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/MainForm.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Initiates a search based on value in parts search text field and refreshes the parts table view with search results.
     * Parts can be searched for by ID or name.
     * @param event Part search button action.
     */

    @FXML
    void onPartsSearchButton(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchTextField.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayAlert(1);
        }
    }



    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     * @param event Parts search text field key pressed.
     */

    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (partSearchTextField.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }


    /**
     * Displays confirmation dialog and removes selected part from associated parts table.
     * Displays error message if no part is selected.
     * @param event Remove button action.
     */


    @FXML
    void onRemoveButton(ActionEvent event) {

        Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
                associatedPartTableView.setItems(associatedParts);
            }
        }
    }


    /**
     * Adds new product to inventory and loads MainScreenController.
     * Text fields are validated with error messages.
     * @param event Save button action.
     * @throws IOException From FXMLLoader.
     */

    @FXML
    void onSaveButton(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = productNameTextField.getText();
            Double price = Double.parseDouble(productPriceTextField.getText());
            int stock = Integer.parseInt(productInventoryTextField.getText());
            int min = Integer.parseInt(productMinTextField.getText());
            int max = Integer.parseInt(productMaxTextField.getText());

            if (name.isEmpty()) {
                displayAlert(7);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);
                    returnToMainScreen(event);
                }
            }
        } catch (Exception e){
            displayAlert(1);
        }
    }



    /**
     * Validates that min is greater than 0 and less than max
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */

    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }



    /**
     * Validates that inventory level is equal too or between min and max.
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */

    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }

        return isValid;
    }


    /**
     * Displays various alert messages.
     * @param alertType Alert message selector.
     */

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }















}

