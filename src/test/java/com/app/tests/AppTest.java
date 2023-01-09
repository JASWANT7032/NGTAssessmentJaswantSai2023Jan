package com.app.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.page.AddToCartPage;
import com.app.page.UserNamePage;



public class AppTest {
	WebDriver driver;
	AddToCartPage cart;
	UserNamePage username;
	Properties prop ;
  @BeforeMethod
  public void setup() throws FileNotFoundException, IOException {
	  driver = new ChromeDriver();
	  
	  cart=new AddToCartPage(driver);
      username=new UserNamePage(driver);
      
      String rootFolder= System.getProperty("user.dir");
     
       prop = new Properties();
       prop.load(new FileInputStream(rootFolder+"//src//test//resources//loginDetails.properties"));


      
	  driver.manage().window().maximize();
      driver.get(prop.getProperty("appLink"));
      
	  
  }
  @Test
  public void verifyAddToCartPages() throws Exception {
      cart.loginIntoProfile();
      cart.verifyBag();
      cart.addtoBag();
      cart.verifyAfterAdd();

  }
  @Test
  public void verifyUserNamePage() throws Exception
  {
           
     username.loginIntoProfile();
  }
  
  @AfterMethod
  public void close() {
	  driver.quit();
	  
}
}
