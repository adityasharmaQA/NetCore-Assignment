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
import org.openqa.selenium.JavascriptExecutor;
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
	
	By flightName = By.xpath("//span[@class='flexOne makeFlex']//span[@class='boldFont blackText']");
	By flightAmount = By.xpath("//div[@class='paneView']//div[@class='makeFlex priceInfo ']//p[@class='blackText fontSize16 blackFont appendRight12']");
	
	
	//This method store flight data and price into excel sheet(Store in make my Trip Project Excel File)
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
				wb.close();//close the file after writting
			}
		catch(IndexOutOfBoundsException error)
			{
				System.out.println("Print Data Successfully");
			}
		
	}
	
	By priceSlider = By.xpath("//div[@class='rc-slider-handle']");
	
	//This method will decrease price by 40% after store flight and ticket price in 2nd excel sheet
	public void sliderOperation() throws InterruptedException, IOException
	{
		//This will decrease price of flight by 40%
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement a = driver.findElement(priceSlider);

		js.executeScript("arguments[0].setAttribute('style', 'right: 40%;')",a);
		
		a.click();
		
		Thread.sleep(4000);
		
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
						Sheet sheet=wb.getSheet("Sheet2");//go to sheet
						
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
						wb.close();//close the file after writting
					}
				catch(IndexOutOfBoundsException error)
					{
						System.out.println("Print Data Successfully");
					}	
	}
	
}
