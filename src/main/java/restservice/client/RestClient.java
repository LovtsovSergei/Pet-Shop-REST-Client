package restservice.client;


import pet.shop.Product;
import pet.shop.Shop;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RestClient {

    private static Client client = ClientBuilder.newClient();
    private static WebTarget baseTarget = client.target("http://localhost:8081/rest/shop/");

    private static void sayWelcome() {
        System.out.println(baseTarget.request().get(String.class));
    }

    private static void findProductsByDescription(String description) {
        WebTarget target = baseTarget.path("search/" + description);
        List<Product> products = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>() {
                });
        System.out.println(products);
    }

    private static void findProductsById(String id) {
        WebTarget target = baseTarget.path("search/" + id);
        Product product = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Product.class);
        System.out.println(product);
    }

    private static void showAllProducts() {
        WebTarget target = baseTarget.path("all");
        Shop shop = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Shop.class);
        System.out.println(shop);
    }

    private static void buyProductById(String id) {
        WebTarget target = baseTarget.path("buy/" + id);
        String response = target
                .request()
                .get(String.class);
        System.out.println(response);
    }

    private static void buyProductByDescription(String description) {
        WebTarget target = baseTarget.path("buy/" + description);
        String response = target
                .request()
                .get(String.class);
        System.out.println(response);
    }


    private static void addProducts() {
        WebTarget target = baseTarget.path("add");
        Product[] products = new Product[3];
        Product prod1 = new Product("SuperCoolFoodForCats1", "0000000010", 150.00, 5.0);
        Product prod2 = new Product("SuperCoolFoodForCats2", "0000000011", 300.00, 10.0);
        Product prod3 = new Product("SuperCoolFoodForCats3", "0000000012", 600.00, 20.0);
        products[0] = prod1;
        products[1] = prod2;
        products[2] = prod3;

        Response response = target
                .request()
                .post(Entity.json(products));
        System.out.println(response.getStatus());
    }


    public static void main(String[] args) {


//        sayWelcome();
//        findProductsByDescription("beef");
//        findProductsByDescription("pork");
//        findProductsById("00000003");
//        showAllProducts();
//        buyProductById("00000003");
//        showAllProducts();
//        findProductsById("00000003");
//        buyProductByDescription("beef");
//        showAllProducts();
            addProducts();

    }
}
