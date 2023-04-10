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

import static maddux.firstscreen.controller.MainFormController.productToModify;

public class ModifyProductFormController implements Initializable {
    public TableColumn associatedPartIdColumn;
    public TableColumn associatedPartNameColumn;
    public TableColumn associatedPartInventoryColumn;
    public TableColumn associatedPartPriceColumn;
    public TableColumn partIdColumn;
    public TableColumn partNameColumn;
    public TableColumn partInventoryColumn;
    public TableColumn partPriceColumn;
    public Button searchButton;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public TextField idField;
    public TextField nameField;
    public TextField inventoryField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;
    public TextField searchField;
    public Button addButton;
    public Button removePartButton;
    public Button saveButton;
    public Button cancelButton;
    public TableView<Part> associatedPartTableView;
    public TableView<Part> partTableView;

    Product selectedProduct;
    public TextField productPriceText;
    public TextField productInventoryText;
    public TextField productMaxText;
    public TextField productMinText;

    public TextField productNameText;

    public int indexSave;

    private static Product modifiedProduct = null;



    public  void dataPassing(int index,Product productToModify){
        modifiedProduct = productToModify;
        this.indexSave = index;
    }



    /**
     * A search based on value in parts search text field and refreshes the parts table view with search results.
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button action.
     */


    @FXML
    void onSearchButton(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = searchField.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }


        partTableView.setItems(partsFound);

        if (searchField.getText().isEmpty()) {
            alertDisplay(7);
        }


        if (partsFound.isEmpty()) {
            alertDisplay(1);
        }
    }


    /**
     * Displays alert messages for appropriate errors.
     * @param alertType Alert message selector.
     */

    private void alertDisplay(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {

            case 1:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Product not found");
                alertInfo.showAndWait();
                break;

            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error Modifying Product");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;

            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number less than max and greater than zero.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number more than Min and less than Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("No product not selected");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Name is Empty");
                alert.setContentText("Name cannot contain numbers or be empty.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Search field empty");
                alert.setContentText("The search field needs input to search.");
                alert.showAndWait();
                break;
        }
    }

    /**
     * Loads Main form controller.
     *
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
     * Displays confirmation dialog, loads MainScreenController.
     *
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
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */

    private boolean minValid(int min, int max) {

        boolean validated = true;

        if (min <= 0 || min >= max) {
            validated = false;
            alertDisplay(3);
        }

        return validated;
    }




    /**
     * Adds part object selected in the all parts table to the associated parts table.
     * Displays error message if no part is selected.
     * @param event Add button action.
     */
    @FXML
    void onAddButton(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            alertDisplay(5);
        } else {
            associatedParts.add(selectedPart);
            associatedPartTableView.setItems(associatedParts);
        }
    }


    /**
     * Replaces the product in inventory, loads MainScreenController.
     * Text fields are validated with error messages displayed preventing empty or invalid values.
     * @param event Save button clicked.
     * @throws IOException From FXMLLoader.
     */

    @FXML
    void onSaveButton(ActionEvent event) throws IOException {

        try {
            int id = selectedProduct.getId();
            String name = productNameText.getText();
            Double price = Double.parseDouble(productPriceText.getText());

            int stock = Integer.parseInt(productInventoryText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int max = Integer.parseInt(productMaxText.getText());

            if (name.isEmpty()) {
                alertDisplay(6);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(selectedProduct);
                    returnToMainScreen(event);
                }
            }
        } catch (Exception e) {
            alertDisplay(2);
        }
    }


    /**
     * Displays confirmation dialog and removes selected part from associated parts table.
     * Displays error message if no part is selected.
     * @param event remove button clicked.
     */

    @FXML
    void onRemovePartButton(ActionEvent event) {

        Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            alertDisplay(5);
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
     * Initializes controller and populates text fields with product selected in MainFormController.
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedProduct = MainFormController.getProductToModify();
        associatedParts = selectedProduct.getAllAssociatedParts();

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartTableView.setItems(associatedParts);

        idField.setText(String.valueOf(selectedProduct.getId()));
        productNameText.setText(String.valueOf(selectedProduct.getName()));
        productInventoryText.setText(String.valueOf(selectedProduct.getStock()));
        productPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxText.setText(String.valueOf(selectedProduct.getMax()));
        productMinText.setText(String.valueOf(selectedProduct.getMin()));

    }

    /**
     * Refreshes part table view when the search text field is left empty.
     * @param keyEvent Parts search text field key pressed.
     */



    @FXML
    public void onSearchKeyPr(KeyEvent keyEvent) {

        if (searchField.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }




    /**
     * Validates the inventory level by checking the min and max numbers.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */


    private boolean inventoryValid(int min, int max, int stock) {

        boolean validated = true;

        if (stock > max||  stock < min) {
            validated = false;
            alertDisplay(4);
        }

        return validated;
    }



}