package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CreateSellerOrder;

public class SingleOrderTest extends BaseTest {

    @Test
    public void createOrderFlow() {

        CreateSellerOrder order = new CreateSellerOrder(driver, wait);

        order.openSingleOrder();

        order.selectPickup("TEST");

        order.enterBuyerDetails(
            "Rahul Sharma",
            "9876543210",
            "9812345678",
            "rahul@test.com",
            "ORD123"
        );

        order.enterDeliveryAddress(
            "Flat 101, Andheri East",
            "400069",
            "Mumbai",
            "Maharashtra",
            "India"
        );

        order.addProduct(
            "tshirt",
            "856",
            "SKU123",
            "Clothes",
            3,
            "15000"
        );

//        SoftAssert softAssert = new SoftAssert();
//
//        // assert category selected correctly
//        softAssert.assertEquals(
//            order.getSelectedCategory(),
//            "Clothes",
//            "Category 'Clothes' was not selected"
//        );
//
//        // assert unit price enabled after category selection
//        softAssert.assertTrue(
//            order.isUnitPriceEnabled(),
//            "Unit price field is not enabled after selecting category"
//        );

        order.enterPackageDetails("1", "20", "15", "10");
        order.selectDanger("no");
        order.selectPayment("prepaid");
        order.submitOrder();

        // evaluate all soft assertions
//        softAssert.assertAll();
    }
}
