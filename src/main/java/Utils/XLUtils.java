package Utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {
    public static FileInputStream fis;
    public static FileOutputStream fout;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getRowCount(String xlFile, String xlSheet) throws IOException {
        fis = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlSheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fis.close();
        return rowCount;
    }

    public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {
        fis = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlSheet);
        row = ws.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fis.close();
        return cellCount;
    }

    public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fis = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fis.close();
        return data;
    }

     public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException {
         fis = new FileInputStream(xlfile);
         wb = new XSSFWorkbook(fis);
         ws = wb.getSheet(xlsheet);
         row = ws.getRow(rownum);
         cell = row.createCell(colnum);
         if (cell.getCellType() != CellType.BLANK) {
             System.out.println(cell.getCellType());
             cell.setCellType(CellType.BLANK);
         }
         cell.setCellValue(data);
         fout = new FileOutputStream(new File(xlfile));
         wb.write(fout);
         wb.close();
         fis.close();
         fout.close();
     }
}
