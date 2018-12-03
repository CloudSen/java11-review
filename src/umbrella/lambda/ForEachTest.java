package umbrella.lambda;

import java.util.List;

public class ForEachTest {

    public static void main(String[] args) {

        List<Product> products = Product.get5Products();
        products.forEach(product -> {
            System.out.println("=".repeat(30));
            System.out.println(product.toString());
        });
        products.forEach(System.out::println);
    }
}
