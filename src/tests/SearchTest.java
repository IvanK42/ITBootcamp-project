package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.LocationPopupPage;
import pages.SearchResultPages;

public class SearchTest extends BasicTest {

	@Test (priority = 0)
	public void searchTest() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/meals");
		LocationPopupPage location = new LocationPopupPage(this.driver);
		SearchResultPages search = new SearchResultPages(this.driver);
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet("Meal Search Results");
		SoftAssert sAssert = new SoftAssert();
	
		location.setLocation("City Center - Albany");
		
		for (int i = 1; i < 7; i++) {

			XSSFRow row = sheet1.getRow(i);
			
			XSSFCell locationCell = row.getCell(0);
			XSSFCell urlCell = row.getCell(1);
			XSSFCell resultsCell = row.getCell(2);
			
			int results = (int) (resultsCell.getNumericCellValue());

			Thread.sleep(500);
			this.driver.navigate().to(urlCell.getStringCellValue());
			
			location.openLocationDialog();
			location.setLocation(locationCell.getStringCellValue());
			
			Thread.sleep(500);
			Assert.assertEquals(results, search.getSearchSize());
				
			for (int j = 3; j <results+3; j++) {
			sAssert.assertTrue((search.mealName().get(j-3).contains(row.getCell(j).getStringCellValue())),
					"[ERROR] Meal names don't match");
			}
		}
		fis.close();
		wb.close();
		sAssert.assertAll();

	}
}
