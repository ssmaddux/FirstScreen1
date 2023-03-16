package maddux.firstscreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import maddux.firstscreen.model.InHouse;
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

import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPartFormController {
    public Label MachineId;
    public RadioButton outsourced;
    public RadioButton inHouse;
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

    /** Checks the subclass of the Part to be modified and checks the appropriate radio button. Stores the selectedPart
     from Inventory in the respective Part variable. The label for the last argument is set to the appropriate text
     by calling setLabel(), then partIntake() is called to fill the TextFields with the Part's data.
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Inventory.selectedPart.getClass() == InHouse.class) {
            inHouse.setSelected(true);
            inHousePart = (InHouse) Inventory.selectedPart;
        }
        else {
            outsourced.setSelected(true);
            outsourcedPart = (Outsourced) Inventory.selectedPart;
        }
        setLabel();
        partIntake();
    }

    /** When the user clicks the saveButton the TextField data is saved in variables and then an appropriate
     subclass is created with that data. The inHouseRadio button is checked to see if it is selected, if it is
     selected, an InHouse part is created with the appropriate arguments, and if it is not, an Outsourced part
     is created. The index of the Part that is being modified is used to save the modified Part to allParts.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateFields()) {
            savedIndex = Inventory.getIndex(Inventory.selectedPart);
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int inventory = Integer.parseInt(inventoryField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (inHouse.isSelected()) {
                int machineId = Integer.parseInt(seventhArgField.getText());
                InHouse modifiedPart = new InHouse(id, name, price, inventory, min, max, machineId);
                Inventory.updatePart(savedIndex, modifiedPart);
            }
            else {
                String companyName = seventhArgField.getText();
                Outsourced modifiedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
                Inventory.updatePart(savedIndex, modifiedPart);
            }
            closeWindow();
        }
    }

    /** Calls closeWindow() when the user clicks the cancel button.
     */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /** Uses Stage.close() to close the ModifyPart window and sets Inventory.selectedPart to null.
     */
    private void closeWindow() {
        Inventory.selectedPart = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /** Saves validated data from the respective TextFields.
     @return true if all fields are validated, false if any are not.
     */
    public boolean validateFields() {
        if (Inventory.stringCheck(nameField.getText())) {
            name = nameField.getText();
        }
        else {
            AddPart.warnUserValidation(0);
            return false;
        }
        if (Inventory.doubleCheck(priceField.getText())) {
            price = Double.parseDouble(priceField.getText());
        }
        else {
            AddPart.warnUserValidation(1);
            return false;
        }
        if (Inventory.intCheck(invField.getText())) {
            stock = Integer.parseInt(inventoryField.getText());
        }
        else {
            AddPart.warnUserValidation(2);
            return false;
        }
        if (Inventory.intCheck(minField.getText())) {
            min = Integer.parseInt(minField.getText());
        }
        else {
            AddPart.warnUserValidation(3);
            return false;
        }
        if (Inventory.intCheck(maxField.getText())) {
            max = Integer.parseInt(maxField.getText());
        }
        else {
            AddPart.warnUserValidation(4);
            return false;
        }
        if (min > max) {
            AddPart.warnUserValidation(4);
            return false;
        }
        if (stock > max || stock < min) {
            AddPart.warnUserValidation(2);
            return false;
        }
        if (inHouse.isSelected()) {
            if (Inventory.intCheck(seventhArgField.getText())) {
                machineId = Integer.parseInt(seventhArgField.getText());
            }
            else {
                AddPart.warnUserValidation(5);
                return false;
            }
        }
        else if (outsourced.isSelected()) {
            if (Inventory.stringCheck(seventhArgField.getText())) {
                companyName = seventhArgField.getText();
            }
            else {
                AddPart.warnUserValidation(6);
                return false;
            }
        }
        return true;
    }

    /** Changes the label by calling setLabel() when the user selects a radio button.
     */
    public void onRadioSelect(ActionEvent actionEvent) {
        setLabel();
    }

    /** Changes the label text to reflect the selected radio button.
     */
    public void setLabel() {
        if (inHouse.isSelected()) {
            seventhArgLabel.setText("Machine ID");
        }
        else {
            seventhArgLabel.setText("Company Name");
        }
    }

    /** Sets the TextFields to the appropriate text from the Part stored in either InhousePart or OutsourcedPart.
     */
    public void partIntake() {

        if (inHousePart != null) {
            idField.setText(String.valueOf(inHousePart.getId()));
            nameField.setText(String.valueOf(inHousePart.getName()));
            inventoryField.setText(String.valueOf(inHousePart.getStock()));
            priceField.setText(String.valueOf(inHousePart.getPrice()));
            maxField.setText(String.valueOf(inHousePart.getMax()));
            minField.setText(String.valueOf(inHousePart.getMin()));
            seventhArgField.setText(String.valueOf(inHousePart.getMachineId()));
        }
        else if (outsourcedPart != null){
            idField.setText(String.valueOf(outsourcedPart.getId()));
            nameField.setText(String.valueOf(outsourcedPart.getName()));
            inventoryField.setText(String.valueOf(outsourcedPart.getStock()));
            priceField.setText(String.valueOf(outsourcedPart.getPrice()));
            maxField.setText(String.valueOf(outsourcedPart.getMax()));
            minField.setText(String.valueOf(outsourcedPart.getMin()));
            seventhArgField.setText(String.valueOf(outsourcedPart.getCompanyName()));
        }
    }


}
