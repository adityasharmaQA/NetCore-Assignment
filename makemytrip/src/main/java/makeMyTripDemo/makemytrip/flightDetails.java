package makeMyTripDemo.makemytrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class flightDetails {
	
	WebDriver driver;
	String ticketPrice;
	
	public flightDetails(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@BeforeClass
	public void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/flight/search?itinerary=BOM-DEL-16/07/2021_DEL-BOM-20/07/2021&tripType=R&paxType=A-2_C-1_I-1&intl=false&cabinClass=B&ccde=IN&lang=eng");
		driver.manage().window().maximize();
	}

	By flightName = By.xpath("//span[@class='flexOne makeFlex']//span[@class='boldFont blackText']");
	By flightAmount = By.xpath("//div[@class='paneView']//div[@class='makeFlex priceInfo ']//p[@class='blackText fontSize16 blackFont appendRight12']");
	
	@Test
	public void storeData() throws IOException, InterruptedException
	{
		//Get FlightName from WebPage
		ArrayList<String> arrFlightName = new ArrayList<String>();
		List<WebElement> flight = driver.findElements(flightName);
		for(WebElement ele:flight)
		{
			String fname = ele.getText();
			System.out.println(fname);
			arrFlightName.add(fname);
		}
		
		ArrayList<String> arrFlightAmount = new ArrayList<String>();
		List<WebElement> amount = driver.findElements(flightAmount);
		for(WebElement ele1:amount)
		{
			String famount = ele1.getText();
			System.out.println(famount);
			arrFlightAmount.add(famount);
		}
		//Store Flight Name Data into Excel
		try 
			{
				File file=new File("E:\\Eclipse_Workspace\\makemytrip\\ExcelFile\\makeMyTrip.xlsx");
				FileInputStream fis=new FileInputStream(file);//Initialize file location
				XSSFWorkbook wb=new XSSFWorkbook(fis);//Go to workbook file
				Sheet sheet=wb.getSheet("Sheet1");//go to sheet
				
				for(int i=0;i<=arrFlightName.size();i++)
				{
					Thread.sleep(1000);
					Row row=sheet.createRow(i);//go to row
					Cell cell=row.createCell(0);//Create cell at cell no
					cell.setCellValue(arrFlightName.get(i));
					Cell cell1=row.createCell(2);//Create cell at cell no
					cell1.setCellValue(arrFlightAmount.get(i));
					//Now using file output stream we can set the value
					FileOutputStream fos=new FileOutputStream(file);
					wb.write(fos);//Perform write in workbook
				}
				System.out.println("Write Excel Data is completed");
				//wb.close();//close the file after writting
			}
		catch(IndexOutOfBoundsException error)
			{
				System.out.println("Print Data Successfully");
			}
		/*
		ArrayList<String> arrFlightAmount = new ArrayList<String>();
		List<WebElement> amount = driver.findElements(flightAmount);
		for(WebElement ele1:amount)
		{
			String famount = ele1.getText();
			System.out.println(famount);
			arrFlightAmount.add(famount);
		}
		try 
		{
			File file=new File("C:\\Users\\Aditya Sharma\\Desktop\\Sample.xlsx");
			FileInputStream fis=new FileInputStream(file);//Initialize file location
			XSSFWorkbook wb=new XSSFWorkbook(fis);//Go to workbook file
			Sheet sheet=wb.getSheet("Sheet1");//go to sheet
			
			for(int i=0;i<=arrFlightAmount.size();i++)
			{
				Thread.sleep(1000);
				Row row=sheet.createRow(i);//go to row
				Cell cell=row.createCell(2);//Create cell at cell no
				cell.setCellValue(arrFlightAmount.get(i));
				//Now using file output stream we can set the value
				FileOutputStream fos=new FileOutputStream(file);
				wb.write(fos);//Perform write in workbook
			}
			System.out.println("Write Excel Data is completed");
			wb.close();//close the file after writting
		}
		catch(IndexOutOfBoundsException error)
			{
				System.out.println("Print Data Successfully");
			}
			*/
	}
	
}
