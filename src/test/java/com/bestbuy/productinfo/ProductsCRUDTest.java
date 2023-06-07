package com.bestbuy.productinfo;

import com.bestbuy.info.ProductSteps;
import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends ProductTestBase {
    static String name = "Acer-Chromebook" + TestUtils.getRandomValue();
    static String type = "laptop";
    static double price = 1669.89;
    static double shipping = 5.99;
    static String upc = TestUtils.getRandomValue();
    static String description = "The stylish Chromebook";
    static String manufacturer = "Acer";
    static String model = TestUtils.getRandomValue();
    static String url = "https://pisces.bbystatic.com/" + TestUtils.getRandomValue() + ".jpg;maxHeight=640;maxWidth=550";
    static String image = TestUtils.getRandomValue();
    static int pID;

    @Steps
    ProductSteps productSteps;


    @Title("This will create a new Product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(201);
        pID = response.extract().path("id");

    }

    @Title("Verify if the Product was added to the Application")
    @Test
    public void test002() {
        int productID = productSteps.getProductID(pID);
        Assert.assertEquals(productID,pID);
    }

    @Title("This will update Product")
    @Test
    public void test003() {
        name = name + "updated";
        productSteps.updateProduct(pID, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        //HashMap<String, Object> productMap = productSteps.getProductInfoByID(pID);
          //Assert.assertThat(productMap, hasValue(pID));

        int productID = productSteps.getProductID(pID);
        Assert.assertEquals(productID,pID);
    }

    @Title("This will delete product and verify that product is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(pID).statusCode(204);
        productSteps.getProductById(pID).statusCode(404);
    }
}
