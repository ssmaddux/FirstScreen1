package maddux.firstscreen;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class ModifyPartFormController {
    public Label MachineId;
    public RadioButton outsourced;
    public RadioButton inHouse;

    public void onInHouse(ActionEvent actionEvent) {
        MachineId.setText("Machine Id");

    }

    public void onOutsourced(ActionEvent actionEvent) {
        MachineId.setText("Company Name");

    }
}
