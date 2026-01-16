package tests;

import base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.SellerLoginPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest extends BaseDriver {

    protected WebDriverWait wait;

    @BeforeSuite
    public void setUp() throws InterruptedException {

        startBrowser();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // LOGIN ONCE
        driver.get("https://seller.shypbuddy.net/sign-in");

        SellerLoginPage login = new SellerLoginPage(driver);
        login.login("Harshit.d@shypbuddy.com", "H@rsh_2025");

        // VERIFY LOGIN
        Assert.assertTrue(
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[.//h3[normalize-space()='Create Order']]")
                )
            ).isDisplayed(),
            "Login failed – stopping suite"
        );
    }

    @AfterSuite
    public void tearDown() {
        stopBrowser();
    }
    
   
    
}
