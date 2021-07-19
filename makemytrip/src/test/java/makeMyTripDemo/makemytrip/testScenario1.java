package makeMyTripDemo.makemytrip;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class testScenario1 {
	
	HomePage h;
	flightListPage f;
	flightDetails fd;
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	ExtentSparkReporter spark;
	
	@BeforeClass
	public void init()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@BeforeTest
	public void generateReport()
	{
		report = new ExtentReports();
		spark = new ExtentSparkReporter("E:/spark.html");
		report.attachReporter(spark);
	}
	
	
	@Test(priority=1)
	public void flightBooking() throws InterruptedException
	{
		test = report.createTest("Validate User can search flight according to requirement");
		h = new HomePage(driver);
		test.log(Status.PASS, "Browser opened successfully");
		h.selectOriginAndDestination();
		test.log(Status.PASS, "Origin and Destination selected successfully");
		h.selectDate();
		test.log(Status.PASS, "Date succesfully selected");
		h.addPassenger();
		test.log(Status.PASS, "Passenger successfully entered");
		h.submitFlightDetials();
		test.log(Status.PASS, "Flight Details successfully submitted");
	}
	
	@Test(priority=2)
	public void validation()
	{
		test = report.createTest("Verify valid detial shown in flight detials Page");
		f = new flightListPage(driver);
		f.validateDetials();
		test.log(Status.PASS, "Flight detials are valid");
	}
	
	@Test(priority=3)
	public void readWriteFlightData() throws IOException, InterruptedException
	{
		test = report.createTest("Verify Flight detials stored in Excel Sheet");
		fd = new flightDetails(driver);
		fd.storeData();
		test.log(Status.PASS, "Flight name and ticket price successfully store in excel sheet");
	}
	
	@Test(priority=4)
	public void slidePriceAndStoreData() throws InterruptedException, IOException
	{
		test = report.createTest("Verify fight price decrease by 40% and data stored in ExcelSheet");
		fd = new flightDetails(driver);
		fd.sliderOperation();
		test.log(Status.PASS, "Flight name and ticket price successfully store in excel sheet");
	}
	@AfterTest
	public void flushReport()
	{
		report.flush();
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
}
