package maddux.firstscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInventoryLevelCol;
    public TableColumn productPriceCol;
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryLevelCol;
    public TableColumn partPriceCol;
    public Button partsAddB;
    public Button partsModifyButton;
    public Button PartsDeleteButton;
    public Button productsAddButton;
    public Button productsModifyButton;
    public Button productsDeleteButton;
    public Button paneExitButton;


    public TextField searchBarPart;
    public TextField searchBarProduct;
    public Button searchButtonPart;
    public Button searchButtonProducts;


    private static boolean firstTime = true;

    private void addTestData() {
        if (!firstTime) {
            return;
        }
        firstTime = false;
        Outsourced O = new Outsourced(1,"bab",10.5,55,2,9);
        Inventory.addPart(O);
        InHouse I = new InHouse(2,"yelp",56.2,89,22,100);
        Inventory.addPart(I);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTestData();


// Creating identifiers for the columns in the parts/products panes in the main form.

        partsTable.setItems(Inventory.getTheInventory());
        productsTable.setItems(Inventory.getTheInventory());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

 //temporary data for proving functionality of code.
//        allParts.add(new Part(1,"muffler", 200, 376));
//        allParts.add(new Part(27,"belt", 78, 50));
//        allParts.add(new Part(43,"condenser", 1000, 200));
//        allParts.add(new Part(22,"lugnut", 25, 10));
//        allParts.add(new Part(700,"headlight", 1, 250));
//        allParts.add(new Part(7,"oil pan", 56, 10));
//
//        allProducts.add(new Product(7,"wizzle", 20, 36));
//        allProducts.add(new Product(2,"whiscker", 88, 500));
//        allProducts.add(new Product(3,"knocker", 1800, 210));
//        allProducts.add(new Product(12,"kent", 251, 101));
//        allProducts.add(new Product(100,"super", 11, 12345));
//        allProducts.add(new Product(79,"shmitl", 53, 1));

//        Outsourced O = new Outsourced(1,"bab",10.5,55,2,9);
//        Inventory.addPart(O);
//        InHouse I = new InHouse(2,"yelp",56.2,89,22,100);




    }

    //Null Pointer exception here. Took more than 5 hours to fix. Fixed by changing periods (.) to slashes (/) and determining the appropriate file structure.
// windowing/ changing to appropriate screens using windowing by using onaction buttons in the mainform on the parts and products panes.
    public void partsOnAddB(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/AddPartForm.fxml"));
        Stage stage = (Stage) partsAddB.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }



    public void partsOnModifyButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/ModifyPartForm.fxml"));
        Stage stage = (Stage) partsAddB.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    public void partsOnDeleteButton(ActionEvent actionEvent) {
        System.out.println("On delete clicked");
    }

    public void productsOnAddButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/AddProductForm.fxml"));
        Stage stage = (Stage) productsAddButton.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    public void productsOnModifyButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/ModifyProductForm.fxml"));
        Stage stage = (Stage) productsAddButton.getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    public void productsOnDeleteButton(ActionEvent actionEvent) {
        System.out.println("On delete clicked");
    }

    public void onExitButtonClick() {
        System.out.println("Exit button clicked");
    }

    public void onSearchBarPart(ActionEvent actionEvent) {
    }

    public void onSearchBarProduct(ActionEvent actionEvent) {
    }


    public void onSearchButtonParts(ActionEvent actionEvent) {

    }

    public void onSearchButtonProducts(ActionEvent actionEvent) {

    }

    private ObservableList<Part> SearchByPartName(String PartialName){
        ObservableList<Part> NamedParts = FXCollections.observableArrayList();

        ObservableList<Part> AllParts = Inventory.getTheInventory();

        for(Part p: ) {
            if(p.getName().contains(PartialName)){
                NamedParts.add(p);
            }
        }


        return NamedParts;
    }
    }


}