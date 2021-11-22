package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import util.HelperMethod;

public class Multiplication {
	WebDriver driver;
	HelperMethod hp;
	String  actRes;
	String expRes;
	
	@Before
	 public void setup() {
		  hp=new HelperMethod(driver);
		  
	  }
	
	@Given("user enter {int} and {int}")
	public void user_enter_and(Integer int1, Integer int2) {
		 hp.launchBrowser("chrome");
		  hp.openApp("https://www.calculator.net/");
		  
		  hp.clickBtn(getNumber(4));
		  hp.clickBtn(getNumber(2));
		  hp.clickBtn(getNumber(3));

		  hp.clickBtn(By.xpath("//span[contains(text(),'×')]"));
		  
		  hp.clickBtn(getNumber(5));
		  hp.clickBtn(getNumber(2));
		  hp.clickBtn(getNumber(5));
	}

	@Given("click on multiple")
	public void click_on_multiple() {
	   
	}

	@Then("verify {int}")
	public void verify(Integer int1) {
		actRes=hp.elementText(By.xpath("//div[@id='sciOutPut']")).trim();
		 expRes=String.valueOf(int1);
		 Assert.assertEquals(actRes, expRes);
	}

	

	public static By getNumber(int num) {
		
		  return By.xpath("//span[contains(text(),'"+num+"')]");
		  
	  }

}