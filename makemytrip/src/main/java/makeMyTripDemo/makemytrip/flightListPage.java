package makeMyTripDemo.makemytrip;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class flightListPage {
	WebDriver driver;

	public flightListPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@BeforeClass
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/flight/search?itinerary=BOM-DEL-16/07/2021_DEL-BOM-20/07/2021&tripType=R&paxType=A-2_C-1_I-1&intl=false&cabinClass=B&ccde=IN&lang=eng");
		driver.manage().window().maximize();
	}

	By originReadValue = By.xpath("//input[@id='fromCity']");
	By destinationReadValue = By.xpath("//input[@id='toCity']");
	
	@Test(priority=1)
	public void validateDetials()
	{
		HomePage h = new HomePage(driver);
		String orgCity1 = h.originCity;
		String desCity1 = h.destinationCity;
		System.out.println(orgCity1+desCity1);
		try
			{
				//Split India from Origin and Destination to validate correct origin and destination are showing in next page or not
				String orgCity = driver.findElement(originReadValue).getAttribute("value");
				String[] orgCityarr = orgCity.split(",",2);
				String firstCity = orgCityarr[0];
				System.out.println(firstCity);
				Assert.assertEquals(orgCity1, firstCity);
			}
		catch(AssertionError e)
			{
				System.out.println("Different value - Origin");
			}
		
		try
			{
				//Split for destination 
				String desCity = driver.findElement(destinationReadValue).getAttribute("value");
				String[] desCityarr = desCity.split(",",2);
				String secondCity = desCityarr[0];
				System.out.println(secondCity);
				Assert.assertEquals(desCity1, secondCity);
				//System.out.println(desCity);
			}
		catch(AssertionError e)
			{
				System.out.println("Different value - Destination");
			}
	}
	
	
	
	@AfterClass
	public void quitBrowser()
	{
		driver.close();
	}
}
