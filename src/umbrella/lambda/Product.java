package umbrella.lambda;

import java.util.Arrays;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private String manufacturer;
    private long price;
    private int quantity;

    public Product() {

    }

    public Product(String id, String name, String manufacturer, long price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Product setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public Product setPrice(long price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "id:" + this.id
                + " name:" + this.name
                + " price:" + this.price
                + " quantity:" + this.quantity
                + " manufacturer:" + this.manufacturer;
    }

    static List<Product> get5Products() {
        return Arrays.asList(
                new Product("p1", "aaaa", "valve", 30, 50),
                new Product("p2", "bbbb", "origin", 21, 42),
                new Product("p3", "cccc", "tencent", 41, 65),
                new Product("p4", "dbdd", "ea", 11, 42),
                new Product("p5", "eebb", "nexue", 45, 24));
    }
}
