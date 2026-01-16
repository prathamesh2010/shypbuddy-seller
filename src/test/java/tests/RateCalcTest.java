package tests;

import org.testng.annotations.Test;

import pages.CreateSellerOrder;
import pages.EditAddressPage;
import pages.RateCalculatorPage;

public class RateCalcTest extends BaseTest {
	
  @Test  //(dependsOnMethods= "tests.PickupAddressTest.addNewAddress")
  public void rateCalculation() throws InterruptedException {
	  
	  RateCalculatorPage rate = new RateCalculatorPage(driver, wait);
	  
	  rate.rateCalculate();
	  
	  rate.fillCalcDetails(
			    "401305",
			    "400001",
			    "2",
			    "10",
			    "8",
			    "6",
			    "Prepaid",
			    "1500",
			    false
			);
	  
	  rate.findcourier();

	        rate.selectPickup("TEST");

	        rate.enterBuyerDetails(
	            "Rahul Sharma",
	            "9876543210",
	            "9812345678",
	            "rahul@test.com",
	            "ORD123"
	        );

	        rate.enterDeliveryAddress(
	            "Flat 101, Andheri East",
	            "400069",
	            "Mumbai",
	            "Maharashtra",
	            "India"
	        );

	        rate.addProduct(
	            "tshirt",
	            "856",
	            "SKU123",
	            "Clothes",
	            3,
	            "15000"
	        );

//	        SoftAssert softAssert = new SoftAssert();
	//
//	        // assert category selected correctly
//	        softAssert.assertEquals(
//	            order.getSelectedCategory(),
//	            "Clothes",
//	            "Category 'Clothes' was not selected"
//	        );
	//
//	        // assert unit price enabled after category selection
//	        softAssert.assertTrue(
//	            order.isUnitPriceEnabled(),
//	            "Unit price field is not enabled after selecting category"
//	        );

	        rate.enterPackageDetails("1", "20", "15", "10");
	        rate.selectDanger("no");
	        rate.selectPayment("prepaid");
	        rate.submitOrder();

	        // evaluate all soft assertions
//	        softAssert.assertAll();
	    }
  }

