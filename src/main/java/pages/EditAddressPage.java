package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditAddressPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    private By pickupBtn = By.xpath("//a[@href='/address/list_address']");
    private By fastEditBtn = By.xpath(
    	    "//h3[normalize-space()='FAST']/ancestor::li//button[.//svg]"
    	);

    // 🔹 POPUP LOCATORS (ADDED)
   // private By savePopup = By.xpath("//div[contains(text(),'Save address')]");
    //private By noThanksBtn = By.xpath("//button[normalize-space()='No thanks']");

    public EditAddressPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /* ========== EDIT ADDRESS ========== */
    public void editAddress() {
    	

        // Click Pickup Address menu
        wait.until(ExpectedConditions.elementToBeClickable(pickupBtn)).click();

    	 // Ensure page is fully loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h3[normalize-space()='FAST']")
        ));

        WebElement editButton = wait.until(
            ExpectedConditions.elementToBeClickable(fastEditBtn)
        );

        // Scroll card into view (centered)
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", editButton
        );

        // Click normally
        editButton.click();

        // Wait for edit form
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='Tag']")
        ));
    }

    /* ========== CLOSE SAVE POPUP ========== */
//    private void closeSavePopupIfPresent() {
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(savePopup));
//
//            WebElement noThanks = wait.until(
//                ExpectedConditions.elementToBeClickable(noThanksBtn)
//            );
//
//            ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].click();", noThanks);
//
//        } catch (Exception e) {
//            // popup not present → ignore
//        }
//    }

    /* ========== LOCATORS ========== */
    private By tagg = By.xpath("//input[@placeholder='Tag']");
    private By phon = By.xpath("//input[@placeholder='Phone']");
    private By addres = By.xpath("//input[@placeholder='Address']");
    private By landMark = By.xpath("//input[@placeholder='Landmark']");
    private By pin = By.xpath("//input[@placeholder='Pincode']");
    private By city = By.xpath("//input[@placeholder='City']");
    private By state = By.xpath("//input[@placeholder='State']");
    private By country = By.xpath("//input[@placeholder='Country']");
    private By submitbtn = By.xpath("//button[@type='submit']");

    /* ========== CLEAR & ENTER DETAILS ========== */
    public void updateAddress(
            String tag,
            String phone,
            String address,
            String landmark,
            String pincode,
            String cityName,
            String stateName,
            String countryName
    ) {

        clearAndType(tagg, tag);
        clearAndType(phon, phone);
        clearAndType(addres, address);
        clearAndType(landMark, landmark);
        clearAndType(pin, pincode);
        clearAndType(city, cityName);
        clearAndType(state, stateName);
        clearAndType(country, countryName);

        driver.findElement(submitbtn).click();
    }

    /* ========== COMMON CLEAR METHOD ========== */
    private void clearAndType(By locator, String value) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(value);
    }
}
