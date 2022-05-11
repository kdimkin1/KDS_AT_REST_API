package tests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemowebshopTestsHW {
    @Test
    void addToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addToCartWithCookieTest() {
        Integer cartSize = 0;

        ValidatableResponse response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2;")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your " +
                                "<a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addToCartWithCookieWithCheckCartSize() {

        // Step1:  add 1 item into cart and check current size
        Response response1 =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2;")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .extract()
                        .response();

          System.out.println("Step 1: Response .path(\"updatetopcartsectionhtml\"): " + response1.path("updatetopcartsectionhtml"));
          String v1 = response1.path("updatetopcartsectionhtml").toString();
          v1 = v1.substring(v1.indexOf('(') + 1, v1.indexOf(')'));
          System.out.println("Cart size after Step 1: " + v1);
          int int1 = Integer.parseInt(v1);
//          System.out.println("Response int1: " + int1);

        // Step 2: add 2 item into cart and compare current size with result of Step 1 + 2
        Response response2 =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2;")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=2")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .extract()
                        .response();

        System.out.println("Step 2: Response .path(\"updatetopcartsectionhtml\"): " + response2.path("updatetopcartsectionhtml"));
        String v2 = response2.path("updatetopcartsectionhtml").toString();
        v2 = v2.substring(v2.indexOf('(') + 1, v2.indexOf(')'));
        System.out.println("Cart size after Step 2: " + v2);
        int int2 = Integer.parseInt(v2);
//        System.out.println("Response int2: " + int2);

        int int3 = int1+2;
        System.out.println("Current cart size should be: " + int3);

        assertEquals(int3, int2);

    }

    /*
    HttpResponse<String> response = Unirest.post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
  .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
  .header("Cookie", "ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e; Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2; Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2")
  .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
  .asString();
     */
}