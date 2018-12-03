package umbrella.lambda;

import java.util.List;

public class LikeTest {

    public static void main(String[] args) {

        List<Product> products = Product.get5Products();
        System.out.println("~".repeat(30) + " starts with " + "~".repeat(30));
        products.stream().filter(product -> product.getName().startsWith("a"))
                .forEach(product -> {
                    System.out.println("=".repeat(30));
                    System.out.println(product.toString());
                });

        System.out.println("~".repeat(30) + " ends with " + "~".repeat(30));
        products.stream().filter(product -> product.getName().endsWith("b"))
                .forEach(product -> {
                    System.out.println("=".repeat(30));
                    System.out.println(product.toString());
                });

        System.out.println("~".repeat(30) + " contains " + "~".repeat(30));
        products.stream().filter(product -> product.getName().contains("b"))
                .forEach(product -> {
                    System.out.println("=".repeat(30));
                    System.out.println(product.toString());
                });
    }
}
