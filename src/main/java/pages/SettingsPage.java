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

        //Thread.sleep(2000);
    }
    
    

    private By changePartnerBtn = By.xpath("//button[normalize-space()='Set Partner Preference']");
    private By selectPartnerBtn = By.xpath("//button[normalize-space()='Select Partner']");
    // Wait until dropdown content appears
    private By scrollBoxLocator = By.xpath("//div[@data-slot='content' and @data-open='true']");
    //private By addButton = By.xpath("//button[normalize-space()='Add']");
    
    public void partnerpref(String partnerName) throws InterruptedException {

        // 1️⃣ Wait for button to be PRESENT in DOM
        WebElement changeBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(changePartnerBtn)
        );

        // 2️⃣ Ensure it is visible via JS (React sometimes lies to Selenium)
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", changeBtn
        );

        // 3️⃣ HARD WAIT for animations / overlays
        wait.until(driver -> changeBtn.isDisplayed());

        // 4️⃣ CLICK using JS ONLY
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", changeBtn
        );

        // ⏳ let modal animate open
        wait.until(ExpectedConditions.presenceOfElementLocated(selectPartnerBtn));


        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1️⃣ Click Select Partner
        WebElement selectBtn = wait.until(
            ExpectedConditions.elementToBeClickable(selectPartnerBtn)
        );
        js.executeScript("arguments[0].click();", selectBtn);

        // 2️⃣ Wait for dropdown
        WebElement dropdown = wait.until(
            ExpectedConditions.visibilityOfElementLocated(scrollBoxLocator)
        );

        for (int i = 0; i < 20; i++) {

            // 🔥 FIND BY TEXT NODE, NOT getText()
            List<WebElement> options = dropdown.findElements(
                By.xpath(".//*[contains(text(),'" + partnerName + "')]")
            );

            if (!options.isEmpty()) {

                WebElement option = options.get(0);

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
                );

                js.executeScript(
                    "arguments[0].click();",
                    option
                );

                return; // ✅ SUCCESS
            }

            // Scroll dropdown
            js.executeScript(
                "arguments[0].scrollTop = arguments[0].scrollTop + 150",
                dropdown
            );

            try { Thread.sleep(300); } catch (Exception ignored) {}
        }

        throw new RuntimeException("❌ Partner not found: " + partnerName);
    }
        //Thread.sleep(5000);
    

    
    
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    
    public void addButton() throws InterruptedException {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
       	    
    	Thread.sleep(2000);
    }
    
    private By svgButton = By.xpath(
    	    "//div[contains(@class,'fixed') and contains(@class,'inset-0')]//button[.//*[name()='svg']]"
    	);
    public void closeButton() {

        WebElement close = wait.until(
            ExpectedConditions.elementToBeClickable(svgButton)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", close
        );
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
    
    private By rateMasterScrollContainer = By.xpath(
    	    "//div[contains(@class,'overflow-y-auto')]"
    	);
    
    public void viewRatesAndScrollThenClose() throws InterruptedException {

        // 1️⃣ Open popup
        wait.until(ExpectedConditions.elementToBeClickable(viewRatesLink)).click();

        // 2️⃣ Wait for Rate Master table to ensure popup opened
        wait.until(ExpectedConditions.visibilityOfElementLocated(rateMasterPopup));

        // 3️⃣ Get the ACTUAL scrollable container (NOT table)
        WebElement scrollContainer = wait.until(
            ExpectedConditions.visibilityOfElementLocated(rateMasterScrollContainer)
        );

        // 4️⃣ Scroll to bottom
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollTop = arguments[0].scrollHeight",
            scrollContainer
        );

        Thread.sleep(1000);

        // 5️⃣ Scroll back to top (optional)
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollTop = 0",
            scrollContainer
        );

        // 6️⃣ Close popup
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
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