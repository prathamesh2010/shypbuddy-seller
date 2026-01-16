package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By transBtn = By.xpath("//a[@href='/wallet']");
    private By walletTab = By.xpath("//button[normalize-space()='WALLET TRANSACTIONS']");
    private By orderTab  = By.xpath("//button[normalize-space()='ORDER TRANSACTIONS']");
    private By creditTab = By.xpath("//button[normalize-space()='CREDIT TRANSACTIONS']");
    
    public TransactionPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

   
    public void openTransactionPage() throws InterruptedException {

        // Click Transaction menu
        WebElement transMenu = wait.until(
            ExpectedConditions.elementToBeClickable(transBtn)
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", transMenu);

        // Wait until Wallet tab is visible (page loaded)
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(walletTab)
        );
//        // 🔒 HARD WAIT – confirm page loaded
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//button[normalize-space()='WALLET TRANSACTIONS']")
//        ));
//    
        Thread.sleep(5000);
    }

    

    /* ================== FILTERS ================== */

    // (2) Download Format
    private By downloadFormat = By.xpath(
        "//select[option[normalize-space()='Download Format']]");


    private By searchInput = By.xpath("//input[@placeholder='Search AWB or OrderID']");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private By resetBtn = By.xpath("//button[contains(@class,'bg-bluePrimary') and @type='button']");
    
    
    /* ================== COMMON HELPERS ================== */

    private void selectDropdown(By locator, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Select(dropdown).selectByVisibleText(value);
    }

    private void typeAndSearch(String value) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(value);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    
    /* ================== WALLET TRANSACTIONS ================== */

    public void walletTransactions(String format, String searchValue) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(walletTab)).click();

        if (searchValue != null) {
            typeAndSearch(searchValue);
        Thread.sleep(5000);
        }

        if (format != null) {
            selectDropdown(downloadFormat, format);
        }
    }

    /* ================== ORDER TRANSACTIONS ================== */

    public void orderTransactions(String format, String orderIdOrAwb) {
        wait.until(ExpectedConditions.elementToBeClickable(orderTab)).click();

        if (format != null) {
            selectDropdown(downloadFormat, format);
        }

        if (orderIdOrAwb != null) {
            typeAndSearch(orderIdOrAwb);
        }
    }

    /* ================== CREDIT TRANSACTIONS ================== */

    public void creditTransactions(String format, String orderIdOrAwbb) {
        wait.until(ExpectedConditions.elementToBeClickable(creditTab)).click();

        if (format != null) {
            selectDropdown(downloadFormat, format);
        }
        if (orderIdOrAwbb != null) {
            typeAndSearch(orderIdOrAwbb);
        }
        
    }

    /* ================== RESET ================== */

    public void resetFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
    }
}
