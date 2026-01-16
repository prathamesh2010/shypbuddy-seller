package shypbuddySeller1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTest {
	WebDriver driver;
	
	@BeforeTest
	public void initial() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://seller.shypbuddy.net/");
		Thread.sleep(2000);
		 
		
	}
  @Test
  public void f() {
	  System.out.println("firstTest");
  }
}
