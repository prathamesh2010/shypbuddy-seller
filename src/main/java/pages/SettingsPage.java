package pages;

import java.time.Duration;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By settingsIcon = By.xpath("//a[@href='/settings']");
   // private By uploadLogoBtn = By.xpath("//button[contains(text(),'Upload Brand Logo')]");
    private By trackingPageLink = By.xpath("//h3[contains(text(),'Tracking Page')]");
    private By kycSection = By.xpath("//button[normalize-space()='Start KYC']");

    
    
    // Click Settings icon
    public void openSettings() throws InterruptedException {

        WebElement settingsIconEl = wait.until(
            ExpectedConditions.visibilityOfElementLocated(settingsIcon)
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", settingsIconEl);

        // 2️⃣ Wait for KYC button
        WebElement kycBtn = wait.until(
            ExpectedConditions.elementToBeClickable(kycSection)
        );

        // 3️⃣ Click KYC
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", kycBtn);

        // 4️⃣ Wait 2 seconds (as requested)
        Thread.sleep(2000);

        // 5️⃣ Navigate back
        driver.navigate().back();

        // 6️⃣ Ensure Settings page is visible again
        wait.until(ExpectedConditions.visibilityOfElementLocated(kycSection));

        Thread.sleep(5000     );
    }
    
    

    private By changePartnerBtn = By.xpath("//button[normalize-space()='Change Partner Preference']");
    private By selectPartnerBtn = By.xpath("//button[normalize-space()='Select Partner']");
    // Wait until dropdown content appears
    private By scrollBoxLocator = By.xpath("//div[@data-slot='content' and @data-open='true']");
    //private By addButton = By.xpath("//button[normalize-space()='Add']");
    
    public void partnerpref(String partnerName) throws InterruptedException {

        // 1️⃣ Click "Change Partner Preference"
        WebElement changeBtn = wait.until(
            ExpectedConditions.elementToBeClickable(changePartnerBtn)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", changeBtn
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", changeBtn
        );
        
        Thread.sleep(3000);

        // 2️⃣ Click "Select Partner"
        WebElement selectBtn = wait.until(
            ExpectedConditions.elementToBeClickable(selectPartnerBtn)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", selectBtn
        );

        // 3️⃣ Wait for dropdown container
        WebElement scrollBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(scrollBoxLocator)
        );

        // 4️⃣ Get all partner options
        List<WebElement> partners = scrollBox.findElements(
            By.xpath(".//div[contains(@class,'cursor-pointer')]")
        );

        // 5️⃣ Select matching partner
        for (WebElement partner : partners) {

            String text = partner.getText().trim();

            if (text.equalsIgnoreCase(partnerName)) {

                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", partner
                );

                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", partner
                );

                return;
            }
        }

        throw new RuntimeException("❌ Partner not found: " + partnerName);
    }
    
    
    
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    public void addButton() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    	
    	By svgButton = By.xpath("//button[.//svg]");

    	wait.until(ExpectedConditions.elementToBeClickable(svgButton)).click();
    }
    
 // Open popup
    private By viewRatesLink =
        By.xpath("//p[normalize-space()='Click to view rates']");

    // Rate Master popup container (scrollable)
    private By rateMasterPopup =
        By.xpath("//table[@aria-label='Rate Master Table']");

    // Close button
    private By closeBtn =
        By.xpath("//button[normalize-space()='Close']");
    
    public void viewRatesAndScrollThenClose() {

        // 1️⃣ Open popup
        wait.until(ExpectedConditions.elementToBeClickable(viewRatesLink)).click();

        // 2️⃣ Wait for popup visible
        WebElement popup = wait.until(
            ExpectedConditions.visibilityOfElementLocated(rateMasterPopup)
        );

        // 3️⃣ Scroll inside popup (bottom)
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollTop = arguments[0].scrollHeight", popup
        );

        // Optional: wait to visually confirm
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        // 4️⃣ Scroll back to top (optional)
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollTop = 0", popup
        );
//
//        // 5️⃣ Close popup
//        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
    }
            
//    // Check if Tracking Page section is visible
//    public boolean isTrackingPageVisible() {
//        return driver.findElement(trackingPageLink).isDisplayed();
//    }
//
//    // Check if Upload Brand Logo button exists
//    public boolean isUploadLogoVisible() {
//        return driver.findElement(uploadLogoBtn).isDisplayed();
//    }
//
//    // Check if KYC block exists
//    public boolean isKycBlockVisible() {
//        return driver.findElement(kycSection).isDisplayed();
//    }
}