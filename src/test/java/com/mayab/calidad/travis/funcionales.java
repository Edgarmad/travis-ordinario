package com.mayab.calidad.travis;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertThat;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class funcionales{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static String URL;

  @Before
  public void init() throws Exception {
      URL="https://mern-crud.herokuapp.com/";
      System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver);
      driver = new ChromeDriver();
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit
    
  }
    public void pause(long mills){
      try{
          Thread.sleep(mills);
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  @Test
  public void testAAdd() throws Exception {
      driver.get(URL);
      
      WebElement element= driver.findElement(By.xpath("/html/body/div/div/div[2]/button"));
      
      element.click();
      
      element = driver.findElement(By.name("name"));
      
      element.sendKeys("Alexis");
      
      element = driver.findElement(By.name("email"));
      
      element.sendKeys("alexis@gmail.com");
      
      element = driver.findElement(By.name("age"));
      
      element.sendKeys("20");
            
      element= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
      
      element.click();  
      
     pause(3000);
     
     element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
      
     String success = element.getText();
      
     assertEquals("Successfully added!",success); 
      
      
      driver.close();   
  }
   
  @Test
  public void testBAddError() throws Exception {
      driver.get(URL);
      
     WebElement element= driver.findElement(By.xpath("/html/body/div/div/div[2]/button"));
      
     element.click();
     
     element = driver.findElement(By.name("name"));
      
     element.sendKeys("Alexis");
      
     element = driver.findElement(By.name("email"));
      
     element.sendKeys("alexis@gmail.com");
      
     element = driver.findElement(By.name("age"));
      
     element.sendKeys("20");
      
     element= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
      
     element.click();
      
     pause(5000);
     
     element= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/p"));
     
     String error = element.getText();
     
     assertEquals("That email is already taken.",error);
      
     driver.close();
    
  }
  
  @Test 
  public void testCDelete() throws Exception {
      driver.get(URL);
      
      WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]"));
      
      pause(1000);
      
      String name = element.getText();
      pause(1000);
      
      element = driver.findElement(By.cssSelector("tr:nth-child(1) .black"));
      pause(1000);
      
      element.click();
      pause(1000);
      
      element = driver.findElement(By.cssSelector(".red"));
      pause(1000);
      
      element.click();
      pause(1000);
      
      WebElement e2 = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)"));
      
      pause(3000);
      
      String name2 = e2.getText();
      
      pause(1000);
      
      assertNotEquals(name,name2);
      pause(1000);
      
      driver.close();
    
  }
 @Test
  public void testDDeleteFail() throws Exception {
      driver.get(URL);
      
      WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]"));
      
      pause(1000);
      
      String name = element.getText();
      pause(1000);
      
      element = driver.findElement(By.cssSelector("tr:nth-child(1) .black"));
      pause(1000);
      
      element.click();
      pause(1000);
      
      element = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]"));
      pause(1000);
      
      element.click();
      pause(1000);
      
      WebElement e2 = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)"));
      
      pause(3000);
      
      String name2 = e2.getText();
      
      pause(1000);
      
      assertEquals(name,name2);
      pause(1000);
      
      driver.close();
    
  }    
      @Test
  public void testEEdit() throws Exception {
      driver.get(URL);
      
      WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[5]/button[1]"));
      
      element.click();
      
      element = driver.findElement(By.name("email"));
      
      pause(5000);
      
      element.clear();
      
      element.sendKeys("edited@test.com");
      
      
      element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
      
      element.click();
      
      pause(5000);
      
      element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
      
      String name2 = element.getText();
      
      assertEquals("Successfully updated!",name2);
      
      driver.close();
    
  }
    
      @Test
  public void testFEditFail() throws Exception {
      driver.get(URL);
      
      WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[5]/button[1]"));
      
      element.click();
      
      element = driver.findElement(By.name("email"));
      
      pause(5000);
      
      element.clear();
      
      element.sendKeys("fail@test");
      
      
      element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
      
      element.click();
      
      pause(5000);
      
      element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/p"));
      
      String name2 = element.getText();
      
      assertEquals("Email must be valid.",name2);
      
      driver.close();
  }
    @After
  public void tearDown(){
      driver.quit();
  }
  



    
}

