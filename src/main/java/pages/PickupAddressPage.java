package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PickupAddressPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /* ========== PICKUP ADDRESS ========== */
    private By pickupBtn = By.xpath("//a[@href='/address/list_address']");
    private By addressBtn = By.xpath("//button[normalize-space()='Add Address']");

    public PickupAddressPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    
    public void createAddress() {

        // Click Pickup Address menu
        wait.until(ExpectedConditions.elementToBeClickable(pickupBtn)).click();

        // Wait for Add Address button
        WebElement addBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(addressBtn)
        );

        // Scroll into view
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", addBtn);

        //  JS CLICK (MANDATORY)
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", addBtn);

        // Ensure form is opened
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@id='tag']")
        ));
    }

    /* ========== ADDRESS FORM ========== */

    private By tagg = By.xpath("//input[@id='tag']");
    private By phon = By.xpath("//input[@id='phone']");
    private By addres = By.xpath("//input[@id='address']");
    private By landMark = By.xpath("//input[@id='landmark']");
    private By pin = By.xpath("//input[@id='pincode']");
    private By city = By.xpath("//input[@id='city']");
    private By state = By.xpath("//input[@id='state']");
    private By country = By.xpath("//input[@id='country']");
    private By submitbtn = By.xpath("//button[@type='submit']");

    public void fillDetails(
            String tag,
            String phone,
            String address,
            String landmark,
            String pinn,
            String cit,
            String stat,
            String contry) throws InterruptedException {

        driver.findElement(tagg).sendKeys(tag);
        
        driver.findElement(phon).sendKeys(phone);
        
        driver.findElement(addres).sendKeys(address);
        driver.findElement(landMark).sendKeys(landmark);
        driver.findElement(pin).sendKeys(pinn);
        
        driver.findElement(city).sendKeys(cit);
        driver.findElement(state).sendKeys(stat);
        driver.findElement(country).sendKeys(contry);
        
    }

    private By pickupPincode = By.xpath("//input[@placeholder='Pickup Pincode']");
    
    // ✅ FIXED SUBMIT
    public void submitAddress() {

        WebElement submit = wait.until(
            ExpectedConditions.visibilityOfElementLocated(submitbtn)
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", submit);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(pickupPincode));
    }
}