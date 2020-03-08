package com.shichko.excel;

import com.shichko.reader.Reader;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReadersCollectionExcel extends CollectionToExcel<Reader> {

    final static Logger logger = Logger.getLogger(ReadersCollectionExcel.class);

    static {
        sheetName = "readers";
    }

    public ReadersCollectionExcel() {
        super();
    }

    @Override
    public CellStyle headerStyle() {

        CellStyle headerStyle = workBook.createCellStyle();

        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerStyle.setBorderRight(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);

        return headerStyle;

    }

    @Override
    public void write(List<Reader> readers, String path) {

        try(FileOutputStream out = new FileOutputStream(new File(path))) {

            sheet.setDefaultColumnWidth(12);

            CellStyle dateStyle = workBook.createCellStyle();
            CreationHelper createHelper = workBook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));

            int rowindex = 0 ;
            XSSFRow row = sheet.createRow(rowindex);

            for (int i = 0; i < 7; i++) {
                row.createCell(i).setCellStyle(headerStyle());
            }

            row.getCell(0).setCellValue("Reader ID");
            row.getCell(1).setCellValue("Name");
            row.getCell(2).setCellValue("Surname");
            row.getCell(3).setCellValue("Year");
            row.getCell(4).setCellValue("Email");
            row.getCell(5).setCellValue("Phone");
            row.getCell(6).setCellValue("Book ID");


            for (Reader reader: readers) {

                row = sheet.createRow(++rowindex);

                for (int i = 0; i < 7; i++) {
                    row.createCell(i).setCellStyle(dataStyle());
                }

                row.getCell(0).setCellValue(reader.getId());
                row.getCell(1).setCellValue(reader.getFirstName());
                row.getCell(2).setCellValue(reader.getSecondName());

                if(reader.getBirthYear() != null) {
                    row.getCell(3).setCellValue(reader.getBirthYear().getValue());
                }

                row.getCell(4).setCellValue(reader.getEmail());

                if(reader.getPhone() != null) {
                    row.getCell(5).setCellValue(reader.getPhone());
                }

                if(reader.getBook() != null) {
                    row.getCell(6).setCellValue(reader.getBook().getId());
                }

            }

            workBook.write(out);

            logger.info("readers' list was successfully written to excel");

        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("readers' list writing to excel failure: " + e.getMessage());
        }

    }

}
