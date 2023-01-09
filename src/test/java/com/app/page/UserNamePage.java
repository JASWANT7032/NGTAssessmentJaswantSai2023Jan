package com.app.page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserNamePage {
	WebDriver driver;
	Properties prop ;

	public UserNamePage(WebDriver driver) throws FileNotFoundException, IOException{
        this.driver=driver;
        
String rootFolder= System.getProperty("user.dir");
        
        prop = new Properties();
        prop.load(new FileInputStream(rootFolder+"//src//test//resources//loginDetails.properties"));
    }

	public void loginIntoProfile() throws Exception
    {
        driver.findElement(By.id("mobileNumberPass")).sendKeys(prop.getProperty("email"));
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.xpath("//button[@class=\"btn primary  lg block submitButton\"]")).click();
        Thread.sleep(31000);
        driver.findElement(By.xpath("//button[@class=\"btn primary  lg block submitButton\"]")).click();
        Thread.sleep(5000);
        driver.get("https://www.myntra.com/my/profile");
        WebElement nameElement=driver.findElement(By.xpath("//div[contains(text(),\"Jaswant\")]"));
        String actualName=nameElement.getText();
        String expectedName="Jaswant";
        Assert.assertEquals(actualName, expectedName,"User Name Doesn't Match");


    }

 


}
