package makeMyTripDemo.makemytrip;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class testScenario1 {
	
	HomePage h;
	flightListPage f;
	flightDetails fd;
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	
	@BeforeClass
	public void init()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test(priority=1)
	public void flightBooking() throws InterruptedException
	{
		//ExtentReports report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReport.html");
		h = new HomePage(driver);
		h.selectOriginAndDestination();
		h.selectDate();
		h.addPassenger();
		h.submitFlightDetials();
	}
	
	@Test(priority=2)
	public void validation()
	{
		f = new flightListPage(driver);
		f.validateDetials();
	}
	
	@Test(priority=3)
	public void readWriteFlightData() throws IOException, InterruptedException
	{
		fd = new flightDetails(driver);
		fd.storeData();
	}
}
