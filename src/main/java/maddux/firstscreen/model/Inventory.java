package maddux.firstscreen.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static Part lookupPart(int partId){
        for(Part p : allParts ) {
            if (p.getId() == partId)
                return p;
        }

        return null;

    }
    public static ObservableList<Part> lookupPart(String part){


        return null;

    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    public static Product lookupProduct(int productId){
        for(Product p : allProducts ) {
            if (p.getId() == productId)
                return p;
        }

        return null;

    }

}
