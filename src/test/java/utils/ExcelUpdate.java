package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUpdate {

    public static void updateResult(String sheetName, String testCaseName, String status) {
        String excelPath = "./output/Innomate_Testcases.xlsx"; // CHANGE THIS TO YOUR EXCEL PATH
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            // Find the header column for "Status" (Assuming it exists, or use a fixed index)
            int statusColIndex = -1;
            Row headerRow = sheet.getRow(2);
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("status")) {
                    statusColIndex = cell.getColumnIndex();
                    break;
                }
            }
            if(statusColIndex == -1) statusColIndex = headerRow.getLastCellNum(); // fallback to append

            // Iterate rows to find the matching Test Case
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell tcCell = row.getCell(2); // Assuming Test Case Name is in Column 0
                    if (tcCell != null && tcCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Cell statusCell = row.createCell(statusColIndex);
                        statusCell.setCellValue(status);
                        break;
                    }
                }
            }

            fis.close();
            FileOutputStream fos = new FileOutputStream(excelPath);
            workbook.write(fos);
            fos.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
