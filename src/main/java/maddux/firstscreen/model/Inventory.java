package maddux.firstscreen.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class Inventory  {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
//    public static Part lookupPart(int partId){
//        for(Part p : allParts ) {
//            if (p.getId() == partId)
//                return p;
//        }
//
//        return null;
//
//    }
//    This will be for searching via Text rather than Id and its needed below as well.
//    public static ObservableList<Part> lookupPart(String part){
//        //String partName = part;
//        ObservableList<Part> kk =FXCollections.observableArrayList();
//        for(Part p : allParts) {
//            if (part.compareTo(p.getName()) == 0)
//                kk.add(p);
//
//        }
//
//
//        return kk;
//
//    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
//    public static Product lookupProduct(int productId){
//        for(Product p : allProducts ) {
//            if (p.getId() == productId)
//                return p;
//        }
//
//        return null;
//
//    }


    public static boolean deletePart (Part selectedPart) {
            if(allParts.contains(selectedPart)) {
                allParts.remove(selectedPart);
                return true;
            } else {
                return false;
            }
        }

    public static boolean deleteProduct (Product selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }
}
