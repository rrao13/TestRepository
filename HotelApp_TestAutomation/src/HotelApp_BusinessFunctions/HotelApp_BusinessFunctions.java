package HotelApp_BusinessFunctions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

public class HotelApp_BusinessFunctions {

	public static Properties prop;
	public static WebDriver driver;


	public void HA_BF_Login (WebDriver driver, String sUserName, String sPassword)

	{
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
	}

	public void HA_Search_Hotel (WebDriver driver, String sLocation, String sHotel)
	{
		driver.findElement(By.cssSelector(prop.getProperty("Txt_Search_Location"))).sendKeys(sLocation);
		driver.findElement(By.xpath(prop.getProperty("Txt_Search_Hotel"))).sendKeys(sHotel);
		driver.findElement(By.name(prop.getProperty("Btn_Search_Search"))).click();


		/*# Referenced by CSS selector
Txt_Search_Location = #location

# Referenced by XPath Locator
Txt_Search_Hotel = .//*[@id='hotels']

# Referenced by name
Btn_Search_Search = Submit
		 */
	}
	
	//This function is used to read data from specified cell of Excel sheet once you give Excelsheet name and path



		public static String HA_GF_readXL (int row, String column, String strFilePath) 

		{

				jxl.Cell c= null;

				int reqCol=0;

				WorkbookSettings ws = null;

				Workbook workbook = null;

				Sheet sheet = null;

				FileInputStream fs = null;

		try{

			fs = new FileInputStream(new File(strFilePath));

			ws = new WorkbookSettings();

			ws.setLocale(new Locale("en", "EN"));

					

			// opening the work book and sheet for reading data

			workbook = Workbook.getWorkbook(fs, ws);

			sheet = workbook.getSheet(0);

			

			// Sanitise given data

			String col = column.trim();

			

			//loop for going through the given row

			for(int j=0; j<sheet.getColumns(); j++)

			{

				jxl.Cell cell = sheet.getCell(j,0);

				if((cell.getContents().trim()).equalsIgnoreCase(col))

				{	

					reqCol= cell.getColumn();

					//System.out.println("column No:"+reqCol);

					c = sheet.getCell(reqCol, row);

					fs.close();

					return c.getContents();

				}

			}

		}

		catch(BiffException be)

		{

			

			System.out.println("The given file should have .xls extension.");

		}

		catch(Exception e)

		{

			e.printStackTrace();

			

		}

		System.out.println("NO MATCH FOUND IN GIVEN FILE: PROBLEM IS COMING FROM DATA FILE");

		

		return null;

		}

		

		

		

		public static int HA_GF_XLRowCount (String strFilePath, String sColumn)

		{

			int k;

			for (k = 1; k < 999; k++)

			{

			

			String sParamVal = 	HA_GF_readXL(k,sColumn,strFilePath);

				if (sParamVal.equals("ENDOFROW"))

					{

						break;

					}

			

			}

			

			return k;

			
		}
		}
	
