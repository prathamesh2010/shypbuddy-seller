package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.util.Assert;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class SellerLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SellerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    
    public void login(String email, String password) throws InterruptedException {

        By emailField = By.id("identifier-field");
        By continueBtn = By.xpath("//button//span[text()='Continue']");
        By passwordField = By.id("password-field");

        WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        );

        
        emailInput.sendKeys(email);
        
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button//span[text()='Continue']"))).click();

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        );

        
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button//span[text()='Continue']"))).click();
        
        Thread.sleep(5000);
        
//        Assert.assertTrue(driver.findElement(By.xpath("//button//span[text()='Continue']")).isDisplayed());
        
    }
}
