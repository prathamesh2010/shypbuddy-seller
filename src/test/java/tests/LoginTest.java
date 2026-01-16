//package tests;
//
//import base.BaseTest;
//import utality.LoginDataProvider;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.SellerLoginPage;
//
//public class LoginTest extends BaseTest {
//
//  
//    @Test(
//        priority =2,
//        dataProvider = "invalidLoginData",
//        dataProviderClass = LoginDataProvider.class
//    )
//    public void invalidLoginTest(String email, String password) throws InterruptedException {
//
//    	 SellerLoginPage loginPage = new SellerLoginPage(driver);
//         loginPage.login(email, password);
//
//
//        Assert.assertTrue(
//            wait.until(
//                ExpectedConditions.visibilityOf(
//                    driver.findElement(By.id("password-field"))
//                )
//            ).isDisplayed(),
//            "Invalid login succeeded unexpectedly"
//        );
//        
//    }
//
//    
//    @Test(
//        priority = 1,
//        dataProvider = "validLoginData",
//        dataProviderClass = LoginDataProvider.class
//    )
//    public void validLoginTest(String email, String password) throws InterruptedException {
//
//        SellerLoginPage loginPage = new SellerLoginPage(driver);
//        loginPage.login(email, password);
//
//        Assert.assertTrue(
//            wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//button[.//h3[normalize-space()='Create Order']]")
//                )
//            ).isDisplayed(),
//            "Valid login failed: Create Order button not visible"
//            
//        );
//       
//    }
//}
