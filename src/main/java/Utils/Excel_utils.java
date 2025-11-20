package Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class Excel_utils {
	 private static Workbook workbook;

	    // Load Excel file (only once)
	    static {
	        try {
	            String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx";
	            FileInputStream fis = new FileInputStream(path);
	            workbook = WorkbookFactory.create(fis);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static String getCellData(String sheetName, int rowNum, int colNum) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) return "Invalid Sheet";

	        Row row = sheet.getRow(rowNum);
	        if (row == null) return "";

	        Cell cell = row.getCell(colNum);
	        if (cell == null) return "";

	        return cell.toString();
	    }

	    // ✅ Get total row count
	    public static int getRowCount(String sheetName) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) return 0;
	        return sheet.getPhysicalNumberOfRows();
	    }

	    // ✅ Get total column count
	    public static int getColCount(String sheetName) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) return 0;
	        Row row = sheet.getRow(0);
	        if (row == null) return 0;
	        return row.getPhysicalNumberOfCells();
	    }

	    // ✅ Close workbook
	    public static void close() throws IOException {
	        if (workbook != null) {
	            workbook.close();
	        }
	    }

}
