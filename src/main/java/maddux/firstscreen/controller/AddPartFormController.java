package maddux.firstscreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import maddux.firstscreen.model.InHouse;
import maddux.firstscreen.model.Inventory;
import maddux.firstscreen.model.Outsourced;


import java.io.IOException;


public class AddPartFormController  {
    public RadioButton inHouse;
    public RadioButton outsourced;
    public Label MachineId;
    public ToggleGroup tgourp;
    public TextField idField;
    public TextField nameField;
    public TextField inventoryField;
    public TextField priceField;
    public TextField maxField;
    public TextField machineIdField;
    public TextField minField;
    public Button saveButton;
    public Button cancelButton;


    /**
     * sets the correct label when the radio button is toggled.
     * @param actionEvent
     */

    public void onInHouse(ActionEvent actionEvent) {
        MachineId.setText("Machine Id");

    }
    /**
     * sets the correct label when the radio button is toggled.
     * @param actionEvent
     */

    public void onOutsourced(ActionEvent actionEvent) {
        MachineId.setText("Company Name");

    }




    /**
     * when cancel button is actioned the main screen is loaded.
     * @param event
     * Issue loading the main screen. Changing all (.) to (/) inbetween the file names was the solution to this problem and copying and pasting the correct address for the main form
     *  from my project files fixed it.
     */

    @FXML
    public void onCancelButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();

    }
    /**
     * Saves the part on the save button press
     * Problem with incorrect indentation with the white space in the try catch block created problems running the onSaveButton method.
     *
     * @param event
     */
    @FXML
    void onSaveButton(ActionEvent event) throws IOException {
        try {

            // Create random number and multiply by 100, this will keep it from overlapping with products.
            int uniqueID = (int) (Math.random() * 100);


            String name = nameField.getText();
            int inStock = Integer.parseInt(inventoryField.getText());
            double price = Double.parseDouble(priceField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());

            int machineID = 0;
            String companyName;


            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max must be greater than min.");
                alert.showAndWait();
                return;
            }

            else if (inStock < min || max < inStock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be between min and max.");
                alert.showAndWait();
                return;
            }
            else


            if (outsourced.isSelected()) {
                companyName = machineIdField.getText();
                Outsourced addPart = new Outsourced(uniqueID, name, price, inStock, min, max, companyName);
                Inventory.addPart(addPart);
            }
            if (inHouse.isSelected()) {
                machineID = Integer.parseInt(machineIdField.getText());
                InHouse addPart = new InHouse(uniqueID, name, price, inStock, min, max, machineID);
                Inventory.addPart(addPart);
            }
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/MainForm.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect value");
            alert.showAndWait();

        }
    }


}