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

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public TextField searchBarPart;
    public TextField searchBarProduct;
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


// Creating identifiers for the columns in the parts/products panes in the main form.

        partsTable.setItems(allParts);
        productsTable.setItems(allProducts);

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

// temporary data for proving functionality of code.
        allParts.add(new Part(1,"muffler", 200, 376));
        allParts.add(new Part(27,"belt", 78, 50));
        allParts.add(new Part(43,"condenser", 1000, 200));
        allParts.add(new Part(22,"lugnut", 25, 10));
        allParts.add(new Part(700,"headlight", 1, 250));
        allParts.add(new Part(7,"oil pan", 56, 10));

        allProducts.add(new Product(7,"wizzle", 20, 36));
        allProducts.add(new Product(2,"whiscker", 88, 500));
        allProducts.add(new Product(3,"knocker", 1800, 210));
        allProducts.add(new Product(12,"kent", 251, 101));
        allProducts.add(new Product(100,"super", 11, 12345));
        allProducts.add(new Product(79,"shmitl", 53, 1));




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


    




// Passing in user text, putting the text into an observable list and running searchbypartname on the text, setting the table with the return value and reseting the search bar.
// Problem here implementing the search bar. Use on action to get text from user not fx:id
// parts pane in main form.
   public void  onSearchButtonParts(ActionEvent actionEvent) {
        String q = searchBarPart.getText();

        ObservableList<Part> Parts = SearchByPartName(q);

        partsTable.setItems(Parts);
        searchBarPart.setText("");


   }



// creation  of search function with for loop for parts pane of the main form.
    private ObservableList<Part> SearchByPartName(String PartialName){
        ObservableList<Part> NamedParts = FXCollections.observableArrayList();

        ObservableList<Part> AllParts = Part.getAllParts();

        for(Part p: allParts) {
            if(p.getName().contains(PartialName)){
                NamedParts.add(p);
            }
        }


        return NamedParts;
    }

    // Passing in user text, putting the text into an observable list and running searchbyproductname on the text, setting the table with the return value and reseting the search bar.
// Problem here implementing the search bar. Use on action to get text from user not fx:id
// products pane in main form.

    public void  onSearchButtonProducts(ActionEvent actionEvent) {
        String T = searchBarProduct.getText();

        ObservableList<Product> Products = SearchByProductName(T);

        productsTable.setItems(Products);
        searchBarProduct.setText("");


    }



    // creation  of search function with for loop for products pane of the main form.
    private ObservableList<Product> SearchByProductName(String PartialName){
        ObservableList<Product> NamedProducts = FXCollections.observableArrayList();

        ObservableList<Product> AllProducts = Product.getAllProducts();

        for(Product Pr: allProducts) {
            if(Pr.getName().contains(PartialName)){
                NamedProducts.add(Pr);
            }
        }


        return NamedProducts;
    }



}
