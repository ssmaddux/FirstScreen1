package maddux.firstscreen.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.lang.Character.isAlphabetic;

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

    /** returns an integer index of the product.
     *
     * @param part
     * @return the index of a part as int.
     */
    public static int getIndex (Part part) {
        return allParts.indexOf(part);
    }

    /** replaces a part in allPArts with .set() at a specific index.
     *
     * @param index of the part to be replaced.
     * @param updatedPart the part to be saved over the old part.
     */
    public static void updatePart(int index, Part updatedPart) {
        allParts.set(index, updatedPart);
    }

    /**
     * checks to see if the first character in a string is alphabetic. Returns false if left empyt or is numerical.
     *
     * @param check check = the string to be verified.
     * @return
     */
    public static boolean startWLetter(String check) {
        if (check== null || check== ("")) {
            return true;
        }
        if (isAlphabetic(check.charAt(0))) {
            return true;

        }

        return false;
    }
//    public static boolean startWLetter(String check) {
//        if (isAlphabetic(Integer.parseInt(check))) {
//            return false;
//        }
//        else {
//            return true;
//        }
//
//
//    }



    public static Part  selectedPart = null;
}
