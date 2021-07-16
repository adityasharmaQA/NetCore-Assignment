package makeMyTripDemo.makemytrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class SampleClass {

	public static void setexceldata(String sheetName,int rowNo,int cellNo,String Data) throws IOException
	{
	File file=new File("C:\\Users\\Aditya Sharma\\Desktop\\Sample.xlsx");
	FileInputStream fis=new FileInputStream(file);//Initialize file location
	XSSFWorkbook wb=new XSSFWorkbook(fis);//Go to workbook file
	Sheet sheet=wb.getSheet(sheetName);//go to sheet
	Row row=sheet.getRow(rowNo);//go to row
	Cell cell=row.createCell(cellNo);//Create cell at cell no
	cell.setCellValue(Data);
	//Now using file output stream we can set the value
	FileOutputStream fos=new FileOutputStream(file);
	wb.write(fos);//Perform write in workbook
	wb.close();//close the file after writting
	}
	public static void main(String[] args) throws IOException  {
	//we can pass the row and column and set the data
	//at 3 row and 3 cell Pankaj i have to Write
		
		//setexceldata("Sheet1", 4, 4,"Pankaj");
		
		
		File file=new File("C:\\Users\\Aditya Sharma\\Desktop\\Sample.xlsx");
		FileInputStream fis=new FileInputStream(file);//Initialize file location
		XSSFWorkbook wb=new XSSFWorkbook(fis);//Go to workbook file
		Sheet sheet=wb.getSheet("Sheet1");//go to sheet
		Row row=sheet.createRow(0);//go to row
		Cell cell=row.createCell(1);//Create cell at cell no
		cell.setCellValue("Aditya");
		//Now using file output stream we can set the value
		FileOutputStream fos=new FileOutputStream(file);
		System.out.println("Excel Write is Completed");
		wb.write(fos);//Perform write in workbook
		wb.close();//close the file after writting
	}


}
