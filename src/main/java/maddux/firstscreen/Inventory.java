package maddux.firstscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> theInventory = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        theInventory.add(part);
    }
    public static ObservableList<Part> getTheInventory(){
        return theInventory;
    }
}
