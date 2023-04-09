package Tests;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTests extends Base {
	 public WebDriver driver;
	@Test
	public void Test1() throws IOException, InterruptedException
	{
		 driver=InitializeDriver();
		System.out.println("Test 2");
		Thread.sleep(3000);
		Assert.assertTrue(false); //to intentionally fail testcase
		driver.close();
	}
}
