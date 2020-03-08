package com.shichko.excel;

import com.shichko.book.Author;
import com.shichko.book.Book;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BooksCollectionExcel extends CollectionToExcel<Book> {

    final static Logger logger = Logger.getLogger(BooksCollectionExcel.class);

    static {
        sheetName = "books";
    }

    public BooksCollectionExcel() {
        super();
    }


    @Override
    public void write(List<Book> books, String path) {

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

            row.getCell(0).setCellValue("Book ID");
            row.getCell(1).setCellValue("Title");
            row.getCell(2).setCellValue("Year");
            row.getCell(3).setCellValue("Publisher");
            row.getCell(4).setCellValue("Publishing date");
            row.getCell(5).setCellValue("Copies");
            row.getCell(6).setCellValue("Authors");


            for (Book book: books) {

                row = sheet.createRow(++rowindex);

                for (int i = 0; i < 7; i++) {
                    row.createCell(i).setCellStyle(dataStyle());
                }

                row.getCell(0).setCellValue(book.getId());
                row.getCell(1).setCellValue(book.getName());
                row.getCell(2).setCellValue(book.getYear().getValue());

                if(book.getPublisher() != null) {
                    row.getCell(3).setCellValue(book.getPublisher().getName());
                }
                if(book.getPublishingDate() != null) {
                    row.getCell(4).setCellStyle(dateStyle);
                    row.getCell(4).setCellValue(book.getPublishingDate());
                }
                if(book.getCopiesAmount() != null) {
                    row.getCell(5).setCellValue(book.getCopiesAmount());
                }

                StringBuilder authors = new StringBuilder();

                for(Author author: book.getAuthors()) {
                    authors.append(author.getSecondName());
                    authors.append("\n");
                }

                row.getCell(6).setCellValue(authors.toString());

            }

            workBook.write(out);

            logger.info("books' list was successfully written to excel");

        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("books' list writing to excel failure: " + e.getMessage());
        }
    }
}
