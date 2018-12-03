package umbrella.lambda;


import java.util.List;

public class FilterTest {

    public static void main(String[] args) {

        List<Product> products = Product.get5Products();
        System.out.println("-".repeat(30) + "use OR condition" + "-".repeat(30));
        products.stream().filter(product -> product.getId().equalsIgnoreCase("p1") || product.getId().equalsIgnoreCase("p4"))
                .forEach(product -> {
                    System.out.println("=".repeat(30));
                    System.out.println(product.toString());
                });
        System.out.println("-".repeat(30) + "use AND condition" + "-".repeat(30));
        products.stream().filter(product -> product.getPrice() > 40 && product.getPrice() < 60)
                .forEach(product -> {
                    System.out.println("=".repeat(30));
                    System.out.println(product.toString());
                });
    }
}
