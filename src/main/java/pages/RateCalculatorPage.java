package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RateCalculatorPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	private By calculatebtn = By.xpath(
		    "//a[@href='/calculator']");
	private By calcSub = By.xpath("//button[normalize-space()='Calculate']");
	
	public RateCalculatorPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
	}
	
	public void rateCalculate() {

		 WebElement calcIcon = wait.until(
			        ExpectedConditions.visibilityOfElementLocated(calculatebtn)
			    );

			    ((JavascriptExecutor) driver)
			        .executeScript("arguments[0].click();", calcIcon);

			    // wait for calculator page to load
			    wait.until(ExpectedConditions.visibilityOfElementLocated(pickupPincode));
		
	}
	
	private void clearAndType(By locator, String value) {
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    element.clear();
	    element.sendKeys(value);
	}
	
		private By pickupPincode = By.xpath("//input[@placeholder='Pickup Pincode']");
		private By deliveryPincode = By.xpath("//input[@placeholder='Delivery Pincode']");
		private By actualWeight = By.id("actualWeight");
		private By length = By.id("length");
		private By breadth = By.id("breadth");
		private By height = By.id("height");
		private By shipmentValue = By.id("shipmentValue");
	
		private By prepaidRadio = By.id("paymentTypePrepaid");
		private By codRadio = By.id("paymentTypeCod");
	
		private By dangerousYes = By.id("dangerousGoodsYes");
		private By dangerousNo = By.id("dangerousGoodsNo");
	
		
		
		public void fillCalcDetails(
		        String pickupPin,
		        String deliveryPin,
		        String weight,
		        String l,
		        String b,
		        String h,
		        String paymentType,      // "Prepaid" or "COD"
		        String shipmentVal,
		        boolean isDangerous
		) throws InterruptedException {

		    clearAndType(pickupPincode, pickupPin);
		    clearAndType(deliveryPincode, deliveryPin);
		    clearAndType(actualWeight, weight);

		    clearAndType(length, l);
		    clearAndType(breadth, b);
		    clearAndType(height, h);

		    if (paymentType.equalsIgnoreCase("Prepaid")) {
		        wait.until(ExpectedConditions.elementToBeClickable(prepaidRadio)).click();
		    } else {
		        wait.until(ExpectedConditions.elementToBeClickable(codRadio)).click();
		    }

		    clearAndType(shipmentValue, shipmentVal);

		    if (isDangerous) {
		        wait.until(ExpectedConditions.elementToBeClickable(dangerousYes)).click();
		    } else {
		        wait.until(ExpectedConditions.elementToBeClickable(dangerousNo)).click();
		    }

		    wait.until(ExpectedConditions.elementToBeClickable(calcSub)).click();
		    Thread.sleep(10000);
		}
		
		private By firstCreateShipmentBtn = By.xpath("(//button[normalize-space()='Create Shipment'])[1]");
		
		public void findcourier() throws InterruptedException {
			
			wait.until(ExpectedConditions.elementToBeClickable(firstCreateShipmentBtn)).click();
			Thread.sleep(2000);
		}
		
		
		///////          ////////             /////////////            ///////////
		
		  /* ========== PICKUP ========== */

	    private By pickupInput = By.xpath("//input[@role='combobox']");
	    private By firstPickupOption = By.xpath("(//li[@role='option'])[1]");

	    public void selectPickup(String text) {
	        wait.until(ExpectedConditions.elementToBeClickable(pickupInput)).sendKeys(text);
	        wait.until(ExpectedConditions.elementToBeClickable(firstPickupOption)).click();
	    }

	    /* ========== BUYER DETAILS ========== */

	    private By buyerName = By.id("fullName");
	    private By buyerPhone = By.id("mobileNumber");
	    private By alternateNum = By.id("alternateNumber");
	    private By optionalEmail = By.id("email");
	    private By customerNo = By.id("Channel_Custom_OrderNo");

	    public void enterBuyerDetails(
	        String name, String phone, String alt, String email, String custNo) {

	        driver.findElement(buyerName).sendKeys(name);
	        driver.findElement(buyerPhone).sendKeys(phone);
	        driver.findElement(alternateNum).sendKeys(alt);
	        driver.findElement(optionalEmail).sendKeys(email);
	        driver.findElement(customerNo).sendKeys(custNo);
	    }

	    /* ========== DELIVERY ADDRESS ========== */

	    private By address = By.id("buyerAddress");
	    private By pincode = By.id("buyerPincode");
	    private By city = By.id("buyerCity");
	    private By state = By.id("state");
	    private By country = By.id("country");

	    public void enterDeliveryAddress(
	        String addr, String pin, String cityVal, String stateVal, String countryVal) {

	        driver.findElement(address).sendKeys(addr);
	        driver.findElement(pincode).sendKeys(pin);
	        driver.findElement(city).sendKeys(cityVal);
	        driver.findElement(state).sendKeys(stateVal);
	        driver.findElement(country).sendKeys(countryVal);
	    }

	    /* ========== PRODUCT ========== */

	    // CATEGORY
	    private By categoryInput =
	        By.xpath("//input[@placeholder='Search or select a category']");

	    private By categoryOptions =
	    	    By.xpath("//ul[contains(@class,'overflow-auto')]//li[@class='p-2 cursor-pointer hover:bg-blue-50 dark:hover:bg-slate-700']");

	    	// specific option “Clothes”
	    	private By categoryOptionByText(String text) {
	    	    return By.xpath("//ul[contains(@class,'overflow-auto')]//li[normalize-space()='" + text + "']");
	    	}

	    private By productName = By.id("productName");
	    private By hsnCode = By.id("hsnCode");
	    private By sku = By.id("sku");
	    private By increaseQtyBtn =
	        By.xpath("//button[contains(@class,'plus') or .//svg]");
	    private By unitPrice = By.id("unitPrice");
	    private By addProductBtn =
	        By.xpath("//button[normalize-space()='Add Product']");

	    private void selectCategory(String categoryName) {

	        WebElement input = wait.until(
	            ExpectedConditions.elementToBeClickable(categoryInput)
	        );

	        input.click();
	        input.clear();
	        input.sendKeys(categoryName);

	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(categoryOptions));

	        WebElement option = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(categoryOptionByText(categoryName))
	        );

	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

	        // force blur to trigger change
	        input.sendKeys(Keys.TAB);

	        wait.until(d -> {
	            String value = input.getAttribute("value");
	            return value != null && value.equalsIgnoreCase(categoryName);
	        });
	    }



	    // Get selected category text
	    public String getSelectedCategory() {
	        WebElement input = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(categoryInput)
	        );
	        return input.getAttribute("value");
	    }

	    // Verify unit price enabled
	    public boolean isUnitPriceEnabled() {
	        return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(unitPrice)
	        ).isEnabled();
	    }

	    public void addProduct(
	    	    String name, String hsn, String skuVal, String cat, int qty, String price) {

	    	    driver.findElement(productName).sendKeys(name);
	    	    driver.findElement(hsnCode).sendKeys(hsn);
	    	    driver.findElement(sku).sendKeys(skuVal);

	    	    selectCategory(cat);

	    	    System.out.println("DEBUG category input value = " + getSelectedCategory());
	    	    System.out.println("DEBUG unitPrice enabled = " + isUnitPriceEnabled());

	    	    for (int i = 1; i < qty; i++) {
	    	        wait.until(ExpectedConditions.elementToBeClickable(increaseQtyBtn)).click();
	    	    }

	    	    driver.findElement(unitPrice).sendKeys(price);
	    	    driver.findElement(addProductBtn).click();
	    	}


	    /* ========== PACKAGE ========== */

	    private By weight = By.id("physicalWeight");
	    private By lengthh = By.id("packageLength");
	    private By width  = By.id("packageBreadth");
	    private By heightt = By.id("packageHeight");

	    public void enterPackageDetails(String wt, String l, String w, String h) {
	        driver.findElement(weight).sendKeys(wt);
	        driver.findElement(lengthh).sendKeys(l);
	        driver.findElement(width).sendKeys(w);
	        driver.findElement(heightt).sendKeys(h);
	    }

	    /* ========== DANGEROUS GOODS ========== */

	    private By yes = By.id("dangerousGoodsYes");
	    private By no = By.id("dangerousGoodsNo");

	    public void selectDanger(String type) {
	        if (type.equalsIgnoreCase("no")) {
	            driver.findElement(no).click();
	        } else {
	            driver.findElement(yes).click();
	        }
	    }

	    /* ========== PAYMENT ========== */

	    private By prepaid = By.xpath("//label[contains(text(),'Prepaid')]");
	    private By cod = By.xpath("//label[contains(text(),'Cash On Delivery')]");

	    public void selectPayment(String type) {
	        if (type.equalsIgnoreCase("prepaid"))
	            driver.findElement(prepaid).click();
	        else
	            driver.findElement(cod).click();
	    }

	    private By selectCourier = By.xpath("//button[normalize-space()='Select Courier']");

	    public void submitOrder() {
	        driver.findElement(selectCourier).click();
	    }
	}

		

