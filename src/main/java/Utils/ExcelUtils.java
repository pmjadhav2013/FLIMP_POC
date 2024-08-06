package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	
	public class ExcelUtils 
	{
		
		@Test(dataProvider = "logincredentials")
		public void testing(String username, String password)
		{
			System.out.println(username + "and " + password);
		}
			
			
		@DataProvider (name = "logincredentials")
		public Object[][] dataSupplier() throws IOException {
	        File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\POC_TestData.xlsx");
	        FileInputStream fis = new FileInputStream(excelFile);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheet("data");

	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rowCount][colCount];

	        for (int r = 0; r < rowCount; r++) {
	            XSSFRow row = sheet.getRow(r + 1); // Start from the second row (index 1)

	            for (int c = 0; c < colCount; c++) {
	                XSSFCell cell = row.getCell(c);
	                if (cell == null) {
	                    data[r][c] = ""; // Handle null cells
	                    continue;
	                }

	                CellType cellType = cell.getCellType();

	                switch (cellType) {
	                    case STRING:
	                        data[r][c] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        if (DateUtil.isCellDateFormatted(cell)) {
	                            data[r][c] = cell.getDateCellValue().toString(); // Format date as needed
	                        } else {
	                            data[r][c] = Double.toString(cell.getNumericCellValue());
	                        }
	                        break;
	                    case BOOLEAN:
	                        data[r][c] = cell.getBooleanCellValue();
	                        break;
	                    case FORMULA:
	                        data[r][c] = cell.getCellFormula(); // Evaluate formula if needed
	                        break;
	                    case BLANK:
	                        data[r][c] = "";
	                        break;
	                    default:
	                        data[r][c] = "";
	                }
	            }
	        }

	        // Close the resources
	        workbook.close();
	        fis.close();

	        return data;
	    }	
	}

