package multiplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import util.HelperMethod;

public class TC_1 {
	WebDriver driver;
	HelperMethod hp;
	String  actRes;
	String expRes;
  @Test
  public void f() {
	  
  hp.launchBrowser("chrome");
  hp.openApp("https://www.calculator.net/");
  
  hp.clickBtn(getNumber(4));
  hp.clickBtn(getNumber(2));
  hp.clickBtn(getNumber(3));
  
hp.clickBtn(By.xpath("//span[contains(text(),'×')]"));
  
  hp.clickBtn(getNumber(5));
  hp.clickBtn(getNumber(2));
  hp.clickBtn(getNumber(5));

  

  actRes=hp.elementText(By.xpath("//div[@id='sciOutPut']")).trim();
  System.out.println("Answer:"+actRes);
  expRes="222075";
  
  Assert.assertEquals(actRes, expRes);
  }
  
  @BeforeTest
  public void setup() {
	  hp=new HelperMethod(driver);
	  
  }
  
  public static By getNumber(int num) {
	
	  return By.xpath("//span[contains(text(),'"+num+"')]");
	  
  }
}
