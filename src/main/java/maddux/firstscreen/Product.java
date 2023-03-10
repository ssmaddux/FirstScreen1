package maddux.firstscreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product extends Inventory {



    public Product(int id, String name, int inventory, int price){
        super(id, name, inventory, price);

    }


    /*
    private int id;
    private String name;
    private int inventory;
    private int price;

    public Product(int id, String name, int inventory, int price)
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;


    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getInventory() {
        return inventory;
    }
    public  int getPrice() {
        return price;
    }
*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
}

