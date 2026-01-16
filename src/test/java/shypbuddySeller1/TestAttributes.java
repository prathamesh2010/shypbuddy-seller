package shypbuddySeller1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAttributes {
	
  @Test(invocationCount=4,threadPoolSize=2, invocationTimeOut=15000)
  public void testMethod1() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
	  WebDriver driver = new ChromeDriver();
	  driver.get("https://www.orangehrm.com/");
	  driver.manage().window().maximize();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//a[text()='Solutions']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[text()='Why OrangeHRM']")).click();
	  driver.quit();
  }
}
