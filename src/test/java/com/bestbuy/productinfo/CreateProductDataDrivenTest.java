package com.bestbuy.productinfo;

import com.bestbuy.info.ProductSteps;
import com.bestbuy.testbase.ProductTestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

//@Concurrent(threads = "8x")
//@UseTestDataFrom("src/test/java/resources/testdata/productinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateProductDataDrivenTest extends ProductTestBase {
    @Steps
    ProductSteps productSteps;
    private String name;
    private String type;
    private double price;
    private double shipping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;
    @Title("Data Driven Test for adding multiple products to the application")
    @Test
    public void createMultipleProducts(){
        productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(201);
    }
}


