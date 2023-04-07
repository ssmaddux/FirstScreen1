package maddux.firstscreen.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.lang.Character.isAlphabetic;

/**
 * inventory class. for holding all parts and products.
 */
public class Inventory  {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * adds product to all products
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * passes Part into an observable list and returns the list.
     * @return
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


    /**
     * adds product to all products
     * @param product
     */

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * passes Product into an observable list and returns the list.
     * @return
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * passses part into am observable list
     * @param part
     * @return
     */
    public static ObservableList<Part> lookupPart(Part part) {
        return null;
    }

    /**
     *  passes product into an observable list
     * @param product
     * @return
     */
    public static ObservableList<Product> lookupProduct(Product product) {
        return null;
    }

    /**
     * initalizes product id to 0 for incrementation.
     */
    private static int productId = 0;

    /**
     * increments product Id for randomness.
     * @return
     */

    public static int getNewProductId() {
        return ++productId;
    }

    /**
     *  deletes part from inventory that is selected by the user.
     * @param selectedPart
     * @return
     */
    public static boolean deletePart (Part selectedPart) {
            if(allParts.contains(selectedPart)) {
                allParts.remove(selectedPart);
                return true;
            } else {
                return false;
            }
        }

    /**
     *  delets product from inventory that is selected by the user.
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct (Product selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /** returns an integer index of the product.
     * @param part
     * @return the index of a part as int.
     */
    public static int getIndex (Part part) {
        return allParts.indexOf(part);
    }

    /** replaces a part in allPArts with .set() at a specific index.
     * @param index of the part to be replaced.
     * @param updatedPart the part to be saved over the old part.
     */
    public static void updatePart(int index, Part updatedPart)
    {
        allParts.set(index, updatedPart);
    }

    public static void updateProduct(int index, Product updatedProduct)
    {
        allProducts.set(index, updatedProduct);
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



    /**
     * sets the variable selected part to null.
     */
    public static Part  selectedPart = null;

    /**
     *  the lookup part method gathers all parts from the inventory and checks for part name contained in the search box.
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> alist = FXCollections.observableArrayList();
        for (Part p : Inventory.getAllParts()) {
            if (p.getName().contains(partName)) {
                alist.add(p);
            }
        }

        return alist;
    }

    /**
     *  the lookup part method gathers all products from the inventory and checks for product name contained in the search box.
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> aList = FXCollections.observableArrayList();
        for(Product p: Inventory.getAllProducts()) {
            if (p.getName().contains(productName)) {
                aList.add(p);
            }
        }

        return aList;
    }

    public static Part lookupPart (int partId){
        for (Part p : Inventory.getAllParts()) {
            if (p.getId() == partId) {
                return p;
            }
        }
        return null;
    }

    public static Product lookupProduct (int productId){
        for (Product p: Inventory.getAllProducts()) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }


}
