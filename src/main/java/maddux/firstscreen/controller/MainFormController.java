package maddux.firstscreen.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maddux.firstscreen.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class MainFormController implements Initializable {
    public static Product productToModify;
    public TableView<Product> productsTable;
    public TableColumn<Product, Integer> productIdCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productInventoryLevelCol;
    public TableColumn<Product, Double> productPriceCol;
    public TableView<Part> partsTable;
    public TableColumn<Part,Integer> partIdCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partInventoryLevelCol;
    public TableColumn<Part, Integer> partPriceCol;
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

    /**
     * gets product to modify
     * @return
     */

    public static Product getProductToModify() {
        return productToModify;
    }




    private static boolean firstTime = true;

    /**
     * adds the test data for program testing.
     */

    private void addTestData() {
        if (!firstTime) {
            return;
        }
        firstTime = false;

        Part O = new Outsourced(1, "intake", 10.5, 55, 2, 9, "excalibur");
        Inventory.addPart(O);
        Part I = new InHouse(2, "muffler", 56.2, 89, 22, 100, 58);
        Inventory.addPart(I);

        //these are product objects.
        Product L = new Product(8, "ford Pinto", 13.5, 95, 55, 5000);
        Inventory.addProduct(L);
        Product K = new Product(2, "F-150", 53.2, 9, 2, 1230);
        Inventory.addProduct(K);

    }


    /**
     *  sets apropriate values to appropriate cells from the inventory.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTestData();



// Creating identifiers for the columns in the parts/products panes in the main form.

        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

       // temporary data for proving functionality of code.
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






    }



    /**
     * RUNTIME ERROR occured in this method when I tried to get the text from the search bar part. the erros occured because wrote the method to search for an
     * Integer without using the .parseInt method. The way I fixed it was researching which method can search for an integer in a tex box which is .parseInt.
     * This corrected my runtime error.
     *  search event for parts based on input in the search text field based on Id and Name
     *  problem adding the inventory class, tried many fixes until I realized it was a simple speeling mistake.
     * @param event
     */

    public void onSearchButtonParts(ActionEvent event) {
     //  if (!searchBarPart.getText().trim().isEmpty()) {
            try {
                int partSearch = Integer.parseInt(searchBarPart.getText());
                for (Part p : Inventory.getAllParts()) {
                    if (p.getId() == partSearch) {
                        partsTable.getSelectionModel().select(p);
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                if (searchBarPart.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Input Error");
                    alert.setContentText("The search box is empty");
                    alert.showAndWait();
                    //ignroing the catch to do search by name.
                }

            }
            String partSearch = (searchBarPart.getText());
            ObservableList<Part> aList = Inventory.lookupPart(partSearch);
            if (aList.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setContentText("Part not found");
                alert.showAndWait();
                System.out.println(" part not found");
            } else {
                partsTable.setItems(aList);
            }
     //   }
    }

    /**
     *  search event for products based on input in the search text field based on Id and Name
     * @param event
     */


    public void onSearchButtonProducts(ActionEvent event) {
            try {
                int productSearch = Integer.parseInt(searchBarProduct.getText());
                for (Product p : Inventory.getAllProducts()) {
                    if (p.getId() == productSearch) {
                        productsTable.getSelectionModel().select(p);
                    }
                }
            } catch (NumberFormatException e) {
                if (searchBarProduct.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Input Error");
                    alert.setContentText("The search box is empty");
                    alert.showAndWait();
                    //ignroing the catch to do search by name.
                }

                String productSearch = (searchBarProduct.getText());
                ObservableList<Product> aList = Inventory.lookupProduct(productSearch);
                if (aList.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Input Error");
                    alert.setContentText("Product not found");
                    alert.showAndWait();
                    System.out.println(" Product not found");
                } else {
                    productsTable.setItems(aList);
                }
            }
    }









    /**
     * Loads add part form on add button click.
     * Thew a null pointer exception until the correct file address was plcaed with correct notation.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void partsOnAddB(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/AddPartForm.fxml"));
        Stage stage = (Stage) partsAddB.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }


    /**
     * on part modify button click the modify part table is loaded and the part selected is passed to the part modify controller and form
     * sets an alert message if no part is selected.
     * @param event
     * @throws IOException
     */
    @FXML
    void partsOnModifyButton(ActionEvent event) throws IOException {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        int index = partsTable.getSelectionModel().getSelectedIndex();
        if (selectedPart == null)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a part to modify");
            alert.show();
        }
        ModifyPartFormController mpf = new ModifyPartFormController();
        mpf.dataPassing(index,selectedPart);



        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/ModifyPartForm.fxml"));
        Stage stage = (Stage) partsAddB.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);

        }


//    }


    /**
     *  loads product modify form, displays alert message if no product is selected.
     * @param event
     * @throws IOException
     */


    public void productsOnModifyButton(ActionEvent event) throws IOException {

        productToModify = productsTable.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a product to modify");
            alert.show();

        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/ModifyProductForm.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * on delete button action delete selected part, display confirmation alert, delete part from Inventory.
     * @param actionEvent
     */


    public void partsOnDeleteButton(ActionEvent actionEvent) {
        System.out.println("On delete clicked");
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * loads the add product form.
     * @param actionEvent
     * @throws IOException
     */


    public void productsOnAddButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/maddux/firstscreen/AddProductForm.fxml"));
        Stage stage = (Stage) productsAddButton.getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
    }



    /**
     *  delets selected product on the delete button click, produces confirmation message, deletes selected product from inventory.
     * @param actionEvent
     */

    public void productsOnDeleteButton(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Product selectedDeleteProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedDeleteProduct.getAllAssociatedParts().size() > 0) {
                Alert cantDelete = new Alert(Alert.AlertType.ERROR);
                cantDelete.setTitle("Error Message");
                cantDelete.setContentText("Remove associated parts before you delete the product.");
                cantDelete.showAndWait();
                return;
            }
            Inventory.deleteProduct(selectedProduct);
        }

    }


    /**
     * closes the program on the exit button click by user.
     * @param ExitButton
     */

    public void onExitButtonClick(ActionEvent ExitButton) {
        Stage stage = (Stage) ((Node) ExitButton.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     *  method for search bar part.
     * @param actionEvent
     */
    public void onSearchBarPart(ActionEvent actionEvent) {
    }

    /**
     * method for search bar product.
     * @param actionEvent
     */
    public void onSearchBarProduct(ActionEvent actionEvent) {
    }













}