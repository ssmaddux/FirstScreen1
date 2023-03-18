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





    public void onInHouse(ActionEvent actionEvent) {
        MachineId.setText("Machine Id");

    }

    public void onOutsourced(ActionEvent actionEvent) {
        MachineId.setText("Company Name");

    }




    /**
     * On button press, go to the main screen.
     * @param event
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
     * On button press, save the part.
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

            //Min should be less than max.
            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than minimum.");
                alert.showAndWait();
                return;
            }
            //Inventory should be between the min and max values.
            else if (inStock < min || max < inStock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within min and max.");
                alert.showAndWait();
                return;
            }

            // Print to console for troubleshooting.
            System.out.println(outsourced.isSelected() + " outsourced");
            System.out.println(inHouse.isSelected() + " inhouse");
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