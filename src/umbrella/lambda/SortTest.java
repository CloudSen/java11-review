package umbrella.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {
        List<Product> products = Product.get5Products();
        System.out.println("~".repeat(30) + "before java 8" + "~".repeat(30));
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        products.forEach(System.out::println);
    }
}
