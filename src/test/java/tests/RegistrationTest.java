package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.RegistrationLoginPage;
import utality.RegisterDataProvider;


public class RegistrationTest extends BaseTest {
	
	 @Test(
		        priority = 1,
		        dataProvider = "invalidRegistrationData",
		        dataProviderClass = RegisterDataProvider.class
		    )
		    public void invalidRegisterTest(String firstname,
		    		String lastname,
		    		String email,
		            String mobile,
		            String password) throws InterruptedException {

		 RegistrationLoginPage registerPage = new RegistrationLoginPage(driver);
		 registerPage.register(firstname,
		             lastname,
		             email,
		             mobile,
		             password);


		        Assert.assertTrue(
		            wait.until(
		                ExpectedConditions.visibilityOf(
		                    driver.findElement(By.id("firstName-field"))
		                )
		            ).isDisplayed(),
		            "Invalid registration succeeded unexpectedly"
		        );
		    }

		    
		    @Test(
		        priority = 2,
		        dataProvider = "validRegistrationData",
		        dataProviderClass = RegisterDataProvider.class
		    )
		    public void validRegisterTest(String firstname,
		    		String lastname,
		    		String email,
		            String mobile,
		            String password) throws InterruptedException {


				 RegistrationLoginPage registerPage = new RegistrationLoginPage(driver);
				 registerPage.register(firstname,
				             lastname,
				             email,
				             mobile,
				             password);


		        Assert.assertTrue(
		            wait.until(
		                ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//button[.//h3[normalize-space()='Create Order']]")
		                )
		            ).isDisplayed(),
		            "Valid register failed: Create Order button not visible"
		        );
		    }
}
