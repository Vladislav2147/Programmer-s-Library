package com.ShichkoVlad.Excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public abstract class CollectionToExcel<T> {

    public static String sheetName;
    XSSFWorkbook workBook;
    XSSFSheet sheet;

    public CollectionToExcel() {

        workBook = new XSSFWorkbook();
        sheet = workBook.createSheet(sheetName);

    }
    public CellStyle headerStyle() {

        CellStyle headerStyle = workBook.createCellStyle();

        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBorderLeft(BorderStyle.THICK);
        headerStyle.setBorderRight(BorderStyle.THICK);
        headerStyle.setBorderTop(BorderStyle.THICK);

        return headerStyle;

    }
    public CellStyle dataStyle() {

        CellStyle dataStyle = workBook.createCellStyle();

        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);

        return dataStyle;

    }

    public abstract void write(List<T> items, String path) throws IOException;
}
