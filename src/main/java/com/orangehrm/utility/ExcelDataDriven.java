package com.orangehrm.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ExcelDataDriven {

    DataFormatter dataFormatter = new DataFormatter();

    public Object[][] getExcelData(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row.getCell(j);
                if (j == 0 || j == 4) {
                    String randomString = generateRandomString(10);
                    data[i][j] = randomString;
                    cell.setCellValue(randomString);
                } else {
                    data[i][j] = dataFormatter.formatCellValue(cell);
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();

        workbook.close();
        fis.close();

        return data;

    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    public Object[][] readExcelData(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i][j] = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            data[i][j] = cell.getBooleanCellValue();
                            break;
                        default:
                            data[i][j] = "";
                            break;
                    }
                } else {
                    data[i][j] = "";
                }
            }
        }
        return data;
    }

    public String getValueAtIndex(String filePath,int rowIndex, int colIndex) throws IOException {
        Object[][] data = readExcelData(filePath);

        if (rowIndex >= 0 && rowIndex < data.length && colIndex >= 0 && colIndex < data[0].length) {
            return String.valueOf(data[rowIndex][colIndex]);
        } else {
            return "Index out of bounds";
        }
    }
}
