package makeMyTripDemo.makemytrip;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePage {
	WebDriver driver;
	String originCity="Mumbai";
	String destinationCity="Delhi";
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	By roundTrip = By.xpath("//li[text()='Round Trip']");
	By originLabel = By.xpath("//span[text()='From']");
	By originTextBox = By.xpath("//input[@placeholder='From' and @type='text']");
	By originCityList = By.xpath("//p[contains(text(),'"+originCity+"')]");
	By destinationLabel = By.xpath("//span[text()='To']");
	By destinationTextBox = By.xpath("//input[@placeholder='To' and @type='text']");
	By destinationList = By.xpath("//p[contains(text(),'"+destinationCity+"')]");
	
	
	public void selectOriginAndDestination() throws InterruptedException
	{
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(roundTrip).click();
		Thread.sleep(5000);
		
		//Select Origin
		driver.findElement(originLabel).click();
		Thread.sleep(5000);
		driver.findElement(originTextBox).sendKeys(originCity);
		Thread.sleep(5000);
		driver.findElement(originCityList).click();
		
		//Select Destination
		driver.findElement(destinationLabel).click();
		Thread.sleep(5000);
		driver.findElement(destinationTextBox).sendKeys(destinationCity);
		Thread.sleep(5000);
		driver.findElement(destinationList).click();	
	}
	
	By departureDateLabel=By.xpath("//span[text()='DEPARTURE']");
	
	public void selectDate() throws InterruptedException
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String todayDate= formatter.format(date);
		System.out.println(todayDate);
		
		int DepartureDate= Integer.parseInt(todayDate);
		int bookingDate=DepartureDate+1;
		int returnDate = DepartureDate+5;
		int preDate = DepartureDate-2;

		formatter = new SimpleDateFormat("MMM");
		String currentMonth= formatter.format(date);
		System.out.println(currentMonth);
		
		
		
		//Select Departure Date
		driver.findElement(departureDateLabel).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@aria-label,'"+currentMonth+"')]//p[text()='"+bookingDate+"']")).click();
		
		
		//Validation to check previous date is disable or not
		driver.findElement(departureDateLabel).click();
		boolean previousDate = driver.findElement(By.xpath("//div[contains(@aria-label,'"+currentMonth+"')]//p[text()='"+preDate+"']")).isEnabled();
			if(previousDate==false)
				{
					System.out.println("Previous Departure date is disabled");
				}
			else
				{
					System.out.println("Previous Departure date is Enable");
				}
				
		
		//Select Return Date 5 days after departure date
		driver.findElement(departureDateLabel).click();
		driver.findElement(By.xpath("//div[contains(@aria-label,'"+currentMonth+"')]//p[text()='"+returnDate+"']")).click();
	}

	public void addPassenger() throws InterruptedException
	{
		//Select Adult Passenger
		driver.findElement(By.xpath("//span[text()='Travellers & CLASS']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//ul[@class='guestCounter font12 darkText gbCounter']//li[text()='2'])[1]")).click();
		
		//Select Child Passenger
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//ul[@class='guestCounter font12 darkText gbCounter']//li[text()='1'])[2]")).click();
		
		
		//Select Infant Passenger
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//ul[@class='guestCounter font12 darkText gbCounter']//li[text()='1'])[3]")).click();
		
		//Select Business Class for Passenger
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[@class='guestCounter classSelect font12 darkText']//li[text()='Business']")).click();
		driver.findElement(By.xpath("//button[text()='APPLY']")).click();
		
	}
	
	public void submitFlightDetials() throws InterruptedException
	{
		//Click on Search button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		
	}
}


