package makeMyTripDemo.makemytrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SampleClass {
	WebDriver driver;
	
	@Test(priority=1)
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/flight/search?itinerary=BOM-DEL-19/07/2021_DEL-BOM-20/07/2021&tripType=R&paxType=A-2_C-1_I-1&intl=false&cabinClass=B&ccde=IN&lang=eng");
		driver.manage().window().maximize();
	}
	
	By flightName = By.xpath("//span[@class='flexOne makeFlex']//span[@class='boldFont blackText']");
	@Test(priority=2)
	public ArrayList<String> flightNameMethod()
	{
		ArrayList<String> arrFlightName = new ArrayList<String>();
		List<WebElement> flight = driver.findElements(flightName);
		for(WebElement ele:flight)
		{
			String fname = ele.getText();
			System.out.println(fname);
			arrFlightName.add(fname);
		}
		return arrFlightName;
	}

	By priceSlider = By.xpath("//div[@class='rc-slider-handle']");
	@Test(priority=3)
	public void sliderOperation() throws InterruptedException
	{
		SampleClass sc = new SampleClass();
		sc.flightNameMethod();
		//WebElement sliderBar = driver.findElement(priceSlider);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement a = driver.findElement(priceSlider);

		js.executeScript("arguments[0].setAttribute('style', 'right: 40%;')",a);
		
		a.click();
		
		Thread.sleep(4000);
		
	}
	

}
