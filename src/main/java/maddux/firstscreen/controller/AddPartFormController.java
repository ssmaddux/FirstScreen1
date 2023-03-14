package maddux.firstscreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.Collection;



public class AddPartFormController  {
    public RadioButton inHouse;
    public RadioButton outsourced;
    public Label MachineId;

    public void onInHouse(ActionEvent actionEvent) {
        MachineId.setText("Machine Id");

    }

    public void onOutsourced(ActionEvent actionEvent) {
        MachineId.setText("Company Name");

    }





}