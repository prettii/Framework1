package Tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import PageObjects.LandingPage;
import PageObjects.LoginPage;
import resources.Base;

public class LoginTests extends Base {
	
	Logger log;
	
public	WebDriver driver;
	@Test (dataProvider="getLoginData")
	public void login (String email,String password,String expectedResult) throws IOException
	{
	
	
	LandingPage landingPage=new LandingPage(driver);
	landingPage.myAccountDropdown().click();
	log.debug("clicked on my account dropdown");
	landingPage.loginOption().click();
	
	log.debug("clicked on login option");
	LoginPage loginPage=new LoginPage(driver);
	loginPage.emailAddressField().sendKeys(prop.getProperty("email"));
	log.debug("email address got entered");
	loginPage.passwordField().sendKeys(prop.getProperty("password"));
	log.debug("password got entered");

	loginPage.loginButton().click();
	log.debug("clicked on login option");
	AccountPage accountPage=new AccountPage(driver);
	String actualResult=null;
	try
	{
		Thread.sleep(5000);
		if(accountPage.editAccountInformationOption().isDisplayed()) 
			
		log.debug("user logged in");
			actualResult="Success";
	
	}
	catch(Exception e)
	{
	actualResult="Failure";	
	log.debug("user dint logged in");
	}
Assert.assertEquals(actualResult,expectedResult);	
log.info("login test got passed");
	
	}
	
	
	
	@BeforeMethod
	public void openApplication() throws IOException
	{ 
		log=LogManager.getLogger(LoginTests.class.getName());
		 driver=InitializeDriver();
		// log.debug("browser got launched");
			driver.get(prop.getProperty("url"));
		//	log.debug("Navigated to application URL");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.close();
		log.debug("browser got closed");
	}
	
	@DataProvider
	public Object[][] getLoginData()
	{
		Object[][] data = {{"Test12345@yahoo.com","Test@123","Success"},{"iii@testnew.com","dummy","Failure"}};
		return data;
		
	}
	
}
