package com.app.page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AddToCartPage {
	WebDriver driver;
	Properties prop ;

	public AddToCartPage(WebDriver driver) throws FileNotFoundException, IOException{
		
        this.driver=driver;
        String rootFolder= System.getProperty("user.dir");
        
        prop = new Properties();
        prop.load(new FileInputStream(rootFolder+"//src//test//resources//loginDetails.properties"));
    }
//	
	
	 public void loginIntoProfile() throws Exception
	    {
	        driver.findElement(By.id("mobileNumberPass")).sendKeys(prop.getProperty("email"));
	        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(prop.getProperty("password"));
	        driver.findElement(By.xpath("//button[@class=\"btn primary  lg block submitButton\"]")).click();
	        Thread.sleep(31000);
	        driver.findElement(By.xpath("//button[@class=\"btn primary  lg block submitButton\"]")).click();
	        Thread.sleep(5000);


	    }
	    public void verifyBag()
	    {
	        driver.findElement(By.xpath("//span[contains(text(),\"Bag\")] ")).click();
	        WebElement NoItem=driver.findElement(By.xpath("//div[contains(text(),\"There is nothing in your bag. Let's add some items.\")]"));
	        String Actual=NoItem.getText();
	        String Expected="There is nothing in your bag. Let's add some items.";
	        Assert.assertEquals(Actual, Expected,"There is an Item in the Cart");



	    }
	    public void addtoBag() throws InterruptedException
	    {
	        driver.findElement(By.xpath("//a[@class=\"linkClean\"]")).click();
	        driver.findElement(By.xpath("//input[@placeholder=\"Search for products, brands and more\"]")).sendKeys("Redmi",Keys.ENTER);
	        Thread.sleep(8000);
	        driver.findElement(By.xpath("//img[@title=\"DressBerry Lavender Textured Structured Mobile Pouch\"]")).click();
	        Thread.sleep(5000);

	        ArrayList<String> nT = new ArrayList<String>(driver.getWindowHandles());
	          //switch to new tab
	          driver.switchTo().window(nT.get(1));
	          driver.findElement(By.xpath("//div[contains(text(),\"ADD TO BAG\")]")).click();
	          Thread.sleep(2000);
//	          driver.findElement(By.xpath("//div[contains(text(),\"GO TO BAG\")]")).click();

	    }

	    public void verifyAfterAdd() throws Exception
	    {
	        driver.findElement(By.xpath("//span[contains(text(),\"Bag\")] ")).click();
	        Thread.sleep(2000);
	        
	        WebElement AddtoCart=driver.findElement(By.xpath("//*[text()='1/1 ITEMS SELECTED']"));
	        String MsActual=AddtoCart.getText();
	        String MsExpected="1/1 ITEMS SELECTED";
	        Assert.assertEquals(MsActual, MsExpected,"The item is Not Added");
	    }

	 

   }

