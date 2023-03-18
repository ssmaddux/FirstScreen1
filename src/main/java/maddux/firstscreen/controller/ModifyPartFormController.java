package maddux.firstscreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import maddux.firstscreen.model.Inventory;
import maddux.firstscreen.model.Outsourced;
import maddux.firstscreen.model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPartFormController implements Initializable  {
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
    public TextField machineAndOutsorcedField;
    public InHouse inHousePart = null;
    public Outsourced outsourcedPart = null;
    public   int index;
    public  int indexSave;
    public  String partName;
    public  double price;
    public int machineId;
    public String companyName;
    private static Part modifiedPart = null;
    public Label machineCompanyNameLabel;

    public void onInHouse(ActionEvent actionEvent) {
        MachineId.setText("Machine Id");


    }

    public void onOutsourced(ActionEvent actionEvent) {
        MachineId.setText("Company Name");

    }

    public static void dataPassing(Part partToModify){
        modifiedPart = partToModify;
    }

    /** Checks the subclass of the Part to be modified and checks the appropriate radio button. Stores the selectedPart
     from Inventory in the respective Part variable. The label for the last argument is set to the appropriate text
     by calling setLabel(), then partIntake() is called to fill the TextFields with the Part's data.
     */

//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (Inventory.selectedPart.getClass() == InHouse.class) {
//            inHouse.setSelected(true);
//            inHousePart = (InHouse) Inventory.selectedPart;
//        }
//        else {
//            outsourced.setSelected(true);
//            outsourcedPart = (Outsourced) Inventory.selectedPart;
//        }
//        setLabel();
//        partIntake();

//    }

//    public void sendPart(Part inHouse) {
//        idField.setText(String.valueOf(inHouse.getId));
//    }









    /** When the user clicks the saveButton the TextField data is saved in variables and then an appropriate
     subclass is created with that data. The inHouseRadio button is checked to see if it is selected, if it is
     selected, an InHouse part is created with the appropriate arguments, and if it is not, an Outsourced part
     is created. The index of the Part that is being modified is used to save the modified Part to allParts.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateFields()) {
            index = Inventory.getIndex(Inventory.selectedPart);
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int inventory = Integer.parseInt(inventoryField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (inHouse.isSelected()) {
                int machineId = Integer.parseInt(machineAndOutsorcedField.getText());
                InHouse modifiedPart = new InHouse(id, name, price, inventory, min, max, machineId);
                Inventory.updatePart(indexSave, modifiedPart);
            }
            else {
                String companyName = machineAndOutsorcedField.getText();
                Outsourced modifiedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
                Inventory.updatePart(index, modifiedPart);
            }
            closeWindow();
        }
    }

    /** Calls closeWindow() when the user clicks the cancel button.
     */
    public void onCancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    /** Uses Stage.close() to close the ModifyPart window and sets Inventory.selectedPart to null.
     */
    private void closeWindow() {
        Inventory.selectedPart = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    /** Generates an Alert, and sets the text based on the field code entered.
     @param warningNumber int that correlates with the desired message.
     */
    public static void warnUserValidation(int warningNumber) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        // name
        if (warningNumber == 0) {
            warning.setContentText("Name must begin with an alpha character.");
        }
        // price
        if (warningNumber == 1) {
            warning.setContentText("Price must be a decimal number.");
        }
        // stock
        if (warningNumber == 2) {
            warning.setContentText("Inv must be a whole number between min and max.");
        }
        // min
        if (warningNumber == 3) {
            warning.setContentText("Min must be a whole number, between 0 and max.");
        }
        // max
        if (warningNumber == 4) {
            warning.setContentText("Max must be a whole number, and greater than min.");
        }
        // machineId
        if (warningNumber == 5) {
            warning.setContentText("Machine ID can only contain numbers.");
        }
        if (warningNumber == 6) {
            warning.setContentText("Company name must begin with an alpha character.");
        }
        // companyName
        warning.show();
    }
    /** Checks for a NumberFormatException to determine if the String argument is a double.
     Returns true if a double can be parsed from the String argument. Returns false if the argument is null,
     or if a NumberFormatException occurs.
     "RUNTIME ERROR"
     Initially I ran into runtime errors in the Inventory GUI as I attempted to parse Strings for doubles.
     When the Strings did not contain the correct type of data, I would run into a NumberFormatException.
     I created methods to catch these exceptions and return false to indicate the String did not contain
     a double.
     @param checkMe The String to be checked.
     @return true if checkMe is a double, false if not.
     */
    public static boolean doubleCheck(String checkMe) {
        if (checkMe == null) {
            return false;
        }
        try {
            Double.parseDouble(checkMe);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /** Checks for a NumberFormatException to determine if the String argument is an integer.
     Returns true if an int can be parsed from the String argument.
     Returns false if the argument is null, or if a NumberFormatException occurs.
     "RUNTIME ERROR"
     Initially I ran into runtime errors in the Inventory GUI as I attempted to parse Strings for integers.
     When the Strings did not contain the correct type of data, I would run into a NumberFormatException.
     I created methods to catch these exceptions and return false to indicate the String did not contain
     an integer.
     @param checkMe The String to be checked.
     @return true if checkMe is an int, false if not.
     */
    public static boolean checkNum(String checkMe) {
        if (checkMe == null) {
            return false;
        }
        try {
            Integer.parseInt(checkMe);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /** Saves validated data from the respective TextFields.
     @return true if all fields are validated, false if any are not.
     */
    public boolean validateFields() {
        if (Inventory.startWLetter(nameField.getText())) {
            partName = nameField.getText();
        }
        else {
            ModifyPartFormController.warnUserValidation(0);
            return false;
        }
        if (ModifyPartFormController.doubleCheck(priceField.getText())) {
             price = Double.parseDouble(priceField.getText());
        }
        else {
            ModifyPartFormController.warnUserValidation(1);
            return false;
        }
        int stock;
        if (ModifyPartFormController.checkNum(inventoryField.getText())) {
            stock = Integer.parseInt(inventoryField.getText());
        }
        else {
            ModifyPartFormController.warnUserValidation(2);
            return false;
        }
        int min;
        if (ModifyPartFormController.checkNum(minField.getText())) {
            min = Integer.parseInt(minField.getText());
        }
        else {
            ModifyPartFormController.warnUserValidation(3);
            return false;
        }
        int max;
        if (ModifyPartFormController.checkNum(maxField.getText())) {
            max = Integer.parseInt(maxField.getText());
        }
        else {
            ModifyPartFormController.warnUserValidation(4);
            return false;
        }
        if (min > max) {
            ModifyPartFormController.warnUserValidation(4);
            return false;
        }
        if (stock > max || stock < min) {
            ModifyPartFormController.warnUserValidation(2);
            return false;
        }
        if (inHouse.isSelected()) {
            if (ModifyPartFormController.checkNum(machineAndOutsorcedField.getText())) {
                 machineId = Integer.parseInt(machineAndOutsorcedField.getText());
            }
            else {
                ModifyPartFormController.warnUserValidation(5);
                return false;
            }
        }
        else if (outsourced.isSelected()) {
            if (Inventory.startWLetter(machineAndOutsorcedField.getText())) {
                 companyName = machineAndOutsorcedField.getText();
            }
            else {
                ModifyPartFormController.warnUserValidation(6);
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
            machineAndOutsorcedField.setText("Machine ID");
        }
        else {
            machineAndOutsorcedField.setText("Company Name");
        }
    }
// in addpartcontroller on laptop add in the warnUserValidation method here maybe.
    /** Sets the TextFields to the appropriate text from the Part stored in either InhousePart or OutsourcedPart.
     */
    public void partIntake() {

//        if (inHousePart != null) {
            idField.setText(String.valueOf(modifiedPart.getId()));
            nameField.setText(String.valueOf(modifiedPart.getName()));
            inventoryField.setText(String.valueOf(modifiedPart.getStock()));
            priceField.setText(String.valueOf(modifiedPart.getPrice()));
            maxField.setText(String.valueOf(modifiedPart.getMax()));
            minField.setText(String.valueOf(modifiedPart.getMin()));
        if (modifiedPart instanceof InHouse)
        {
            int m =  ((InHouse)modifiedPart).getMachineId();
            machineAndOutsorcedField.setText(String.valueOf(m));
            inHouse.setSelected(true);
            machineCompanyNameLabel.setText("Machine ID");
        }
        else
        {
            machineAndOutsorcedField.setText(((Outsourced)modifiedPart).getCompanyName());
            outsourced.setSelected(true);
            machineCompanyNameLabel.setText("Company Name");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(modifiedPart.getName());
        partIntake();

    }
}
