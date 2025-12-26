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
//    package utils;
//
//    import java.io.FileInputStream;
//    import java.io.FileOutputStream;
//    import org.apache.poi.ss.usermodel.*;
//    import org.apache.poi.ss.util.CellRangeAddress; // Required for handling merged cells
//    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//    public class ExcelUpdate {
//
//        public static void updateResult(String sheetName, String testCaseName, String status) {
//            String excelPath =  "./output/Innomate_Testcases.xlsx";
//            
//            try (FileInputStream fis = new FileInputStream(excelPath);
//                 Workbook workbook = new XSSFWorkbook(fis)) {
//
//                Sheet sheet = workbook.getSheet(sheetName);
//                
//                // 1. Find Status Column Index
//                int statusColIndex = -1;
//                Row headerRow = sheet.getRow(2);
//                for (Cell cell : headerRow) {
//                    if (cell.getStringCellValue().equalsIgnoreCase("status")) {
//                        statusColIndex = cell.getColumnIndex();
//                        break;
//                    }
//                }
//                if (statusColIndex == -1) statusColIndex = headerRow.getLastCellNum();
//
//                // 2. Find the Row for the TestCase
//                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                    Row row = sheet.getRow(i);
//                    if (row == null) continue;
//
//                    Cell tcCell = row.getCell(2); // Assuming Test Case Name is Column 0
//                    
//                    // We use trim() to avoid issues with extra spaces
//                    if (tcCell != null && tcCell.getStringCellValue().trim().equalsIgnoreCase(testCaseName)) {
//                        
//                        // 3. Handle Merged Status Cells
//                        Cell statusCell = row.getCell(statusColIndex);
//                        if (statusCell == null) statusCell = row.createCell(statusColIndex);
//
//                        // Check if this specific cell is part of a merged region
//                        for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
//                            CellRangeAddress region = sheet.getMergedRegion(m);
//                            
//                            // If the cell we want to write to is inside a merged block
//                            if (region.isInRange(row.getRowNum(), statusColIndex)) {
//                                // We MUST switch to the Top-Left cell of that merge
//                                Row firstRow = sheet.getRow(region.getFirstRow());
//                                statusCell = firstRow.getCell(region.getFirstColumn());
//                                if (statusCell == null) statusCell = firstRow.createCell(region.getFirstColumn());
//                                break; 
//                            }
//                        }
//
//                        statusCell.setCellValue(status);
//                        
//                        // Optional: If you want to color the cell Green/Red
//                         CellStyle style = workbook.createCellStyle();
//                         if(status.equalsIgnoreCase("PASS")) style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//                         else style.setFillForegroundColor(IndexedColors.RED.getIndex());
//                         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                         statusCell.setCellStyle(style);
//
//                        break; // Stop after finding the test case
//                    }
//                }
//
//                // 4. Save File
//                FileOutputStream fos = new FileOutputStream(excelPath);
//                workbook.write(fos);
//                fos.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
