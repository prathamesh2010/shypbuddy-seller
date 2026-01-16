package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://seller.shypbuddy.net/sign-up");
    }

    public void register(
            String firstname,
            String lastname,
            String email,
            String mobile,
            String password
    ) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName-field")))
            .sendKeys(firstname);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName-field")))
        .sendKeys(lastname);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress-field")))
            .sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber-field")))
            .sendKeys(mobile);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
            .sendKeys(password);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("legalAccepted")))
        .click();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button//span[text()='Continue']")
        )).click();
    }
}
