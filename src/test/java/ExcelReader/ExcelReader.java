package ExcelReader;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelReader {

    public static String getCellValue(String xl, String Sheet, int r, int c) {

        try {

            FileInputStream fis = new FileInputStream(xl);
            String data = "";
            Workbook wb = WorkbookFactory.create(fis);

            Cell cell = wb.getSheet(Sheet).getRow(r).getCell(c);

            if (cell.getCellType() == CellType.STRING)
                data = cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC)
                data = String.valueOf(cell.getNumericCellValue()).replace(".0","");


            return data;

        } catch (Exception e) {

            return "";

        }

    }

    public static int getRowCount(String xl, String Sheet) {

        try {

            FileInputStream fis = new FileInputStream(xl);

            Workbook wb = WorkbookFactory.create(fis);

            return wb.getSheet(Sheet).getLastRowNum();

        } catch (Exception e) {

            return 0;

        }

    }

    public static void setCellValue(String xl, String Sheet, int r, int c, String writeValue) {

        try {
            FileInputStream fis = new FileInputStream(xl);
            Workbook wb = WorkbookFactory.create(fis);

            Cell cell = wb.getSheet(Sheet).getRow(r).getCell(c);
            if (cell == null) {
                Row row = wb.getSheet(Sheet).getRow(r);
                cell = row.createCell(c);
            }

            cell.setCellValue(writeValue);


            FileOutputStream fileOut = new FileOutputStream(xl);
            wb.write(fileOut);
            fileOut.close();

            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}