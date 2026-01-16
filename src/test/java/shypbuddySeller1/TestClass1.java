package shypbuddySeller1;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.ITestListnerClass;

@Listeners(ITestListnerClass.class)
public class TestClass1 {
  @Test
  public void testMethod1() {
	  System.out.println("i am inside method 1");
  }
  
  @Test
  public void testMethod2() {
	  System.out.println("i am inside method 2");
  }
  
  @Test
  public void testMethod3() {
	  System.out.println("i am inside method 3");
  }
  
  @Test
  public void testMethod4() {
	  System.out.println("i am inside method 4");
  }
}
