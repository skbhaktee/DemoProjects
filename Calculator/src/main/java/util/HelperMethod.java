package util;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class HelperMethod {

	WebDriver driver;

	public HelperMethod(WebDriver idriver) {
		this.driver = idriver;
	}
	
	public WebDriver launchBrowser(String browser) {
		
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				//driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			else if(browser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				//driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("opera")) {
				WebDriverManager.operadriver().setup();
				driver=new OperaDriver();
			}
			else if(browser.equalsIgnoreCase("chromium")) {
				WebDriverManager.chromiumdriver();
				driver=new ChromeDriver();
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}

	
	public void openApp(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	} 
	
	
	public boolean titleCompare(String expectedtitle) {
		boolean flag = false;
		if (driver.getTitle().equals(expectedtitle)) {
			flag = true;
		} else {
			flag = false;
			Assert.assertEquals(driver.getTitle(), expectedtitle);
		}
		return flag;
	}

	public void enterText(By by, String text) {
		try {
			driver.findElement(by).sendKeys(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clickBtn(By by) {
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String elementText(By by) {
		String text=null;
		try {
			text= driver.findElement(by).getText();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return text;
	}

	public boolean verifyElement(By by, String text) {
		boolean flag = false;
		try {
			String actual = driver.findElement(by).getText();
			if (actual.equals(text)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	public void allCheckBox(By by) {
		try {
			
			List<WebElement> checkbox = driver.findElements(by);
			for (WebElement ele : checkbox) {
				if(!ele.isSelected()) {
					ele.click();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void selectdropDown(By by,int index) {
		try {
			Select sel=new Select(driver.findElement(by));
			sel.selectByIndex(index);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void selectdropDown(By by,String text) {
		try {
			Select sel=new Select(driver.findElement(by));
			sel.selectByVisibleText(text);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void selectdropDown(String value,By by) {
		try {
			Select sel=new Select(driver.findElement(by));
			sel.selectByValue(value);	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public void dragandDrop(By src,By tgt) {
		try {

			Actions act = new Actions(driver);

			WebElement source = driver.findElement(src);

			WebElement target = driver.findElement(tgt);

			act.dragAndDrop(source, target).perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mouseHover(By by) {
		try {

			Actions act = new Actions(driver);

			

			WebElement target = driver.findElement(by);

			act.moveToElement(target).perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public void getScreenShot(String fileloc) {
		try {
			//TakesScreenshot is an interface and type casting with webdriver reference variable
			File from=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			
			int month=LocalDateTime.now().getMonthValue();
			int day=LocalDateTime.now().getDayOfMonth();
			int hour=LocalDateTime.now().getHour();
			int min=LocalDateTime.now().getMinute();
			
			String filename=month+""+day+""+hour+""+min;
			
			
			File to=new File(fileloc+"//"+filename+".jpg");
			FileHandler.copy(from, to);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void scroll(int x,int y) {
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy("+x+","+y+")");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object[][] dataReader(String fileloc) {
		Object[][] obj=null;
		try {
			XSSFWorkbook wb = new XSSFWorkbook(fileloc);
			XSSFSheet sheet = wb.getSheetAt(0);
			int allRows = sheet.getLastRowNum();
			int allCells = sheet.getRow(0).getLastCellNum();
			obj = new Object[allRows][1];

			for (int i = 0; i < allRows; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				String key = "";
				String value = "";
				for (int j = 0; j < allCells; j++) {
					key = sheet.getRow(0).getCell(j).getStringCellValue();
					if (sheet.getRow(i + 1).getCell(j).getCellType().equals(CellType.STRING)) {

						value = sheet.getRow(i + 1).getCell(j).getStringCellValue();

					} else if (sheet.getRow(i + 1).getCell(j).getCellType().equals(CellType.NUMERIC)) {
						value = String.valueOf(sheet.getRow(i + 1).getCell(j).getNumericCellValue());
					} else if (sheet.getRow(i + 1).getCell(j).getCellType().equals(CellType.FORMULA)) {
						value = String.valueOf(sheet.getRow(i + 1).getCell(j).getCellFormula());
					}
					map.put(key, value);

				}
				obj[i][0] = map;

			}
			wb.close();

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}

	public void waitforElement(int time) {
		try {
			Thread.sleep(1000*time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void waitforPage(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}