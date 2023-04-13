package com.graduate.touslestemp.utils;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Size;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.exception.RequestException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.List;

import static com.graduate.touslestemp.utils.FileFactory.PATH_TEMPLATE;

@Component
public class ExportUtils {
    public static ByteArrayInputStream exportStaff(List<Size> staff, String fileName) throws Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        File file;
        FileInputStream fileInputStream;
        try {
            file = ResourceUtils.getFile(PATH_TEMPLATE + fileName);
            fileInputStream = new FileInputStream(file);

        } catch (Exception e) {
            file = FileFactory.createFile(fileName, xssfWorkbook);
            fileInputStream = new FileInputStream(file);

        }
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("sheet 1");
        xssfSheet.createFreezePane(4, 2, 4, 2);

        XSSFFont titleFont = xssfWorkbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);

        XSSFCellStyle titleCellStyle = xssfWorkbook.createCellStyle();
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        titleCellStyle.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.index);
        titleCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        titleCellStyle.setBorderLeft(BorderStyle.MEDIUM);
        titleCellStyle.setBorderRight(BorderStyle.MEDIUM);
        titleCellStyle.setBorderTop(BorderStyle.MEDIUM);
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setWrapText(true);


        XSSFFont dataFont = xssfWorkbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setBold(false);
        dataFont.setFontHeightInPoints((short) 12);

        XSSFCellStyle dataCellStyle = xssfWorkbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setFont(titleFont);
        dataCellStyle.setWrapText(true);

        insertFieldNameAsTitleWorkbook(ExportConfig.staffExport.getCellConfigList(), xssfSheet, titleCellStyle);

        insertDataToWorkbook(xssfWorkbook, ExportConfig.staffExport, staff, dataCellStyle);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        xssfWorkbook.write(outputStream);
        outputStream.close();
        fileInputStream.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }


    private static <T> void insertDataToWorkbook(Workbook workbook, ExportConfig exportConfig, List<T> data, XSSFCellStyle xssfCellStyle) {
        int startRowIndex = exportConfig.getStartRow();
        int sheetIndex = exportConfig.getSheetIndex();
        Class staff = exportConfig.getDataClass();
        List<CellConfig> cellConfigList = exportConfig.getCellConfigList();
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int currentRowIndex = startRowIndex;
        for (T item : data) {
            Row currentRow = sheet.getRow(currentRowIndex);
            if (ObjectUtils.isEmpty(currentRow)) {
                currentRow = sheet.createRow(currentRowIndex);
            }

            insertDataToCell(data, currentRow, cellConfigList, staff, sheet, xssfCellStyle);
            currentRowIndex++;

        }
    }

    private static <T> void insertFieldNameAsTitleWorkbook(List<CellConfig> cellConfigList, Sheet sheet, XSSFCellStyle titleCellStyle) {
        int currentRow = sheet.getTopRow();
        Row row = sheet.createRow(currentRow);
        int i = 0;
        sheet.autoSizeColumn((currentRow));
        for (CellConfig cellConfig : cellConfigList) {
            Cell currentCell = row.createCell(i);
            String fieldName = cellConfig.getFieldName();
            currentCell.setCellValue(fieldName);
            currentCell.setCellStyle(titleCellStyle);
            sheet.autoSizeColumn(i);
            i++;

        }
    }

    private static <T> void insertDataToCell(T data, Row currentRow, List<CellConfig> cellConfigList, Class staff, Sheet sheet, XSSFCellStyle xssfCellStyle) {
        for (CellConfig cellConfig : cellConfigList) {
            Cell currnetCell = currentRow.getCell(cellConfig.getColumnIndex());
            if (ObjectUtils.isEmpty(currnetCell)) {
                currnetCell = currentRow.createCell(cellConfig.getColumnIndex());
            }
            String cellValue = getCellValue(data, cellConfig, staff);

            currnetCell.setCellValue(cellValue);
            sheet.autoSizeColumn(cellConfig.getColumnIndex());
            currnetCell.setCellStyle(xssfCellStyle);
        }
    }

    private static <T> String getCellValue(T data, CellConfig cellConfig, Class staff) {
        String fieldName = cellConfig.getFieldName();

        try {
            Field field = getDeclaredField(staff, fieldName);
            if (!ObjectUtils.isEmpty(field)) {
                field.setAccessible(true);
                return !ObjectUtils.isEmpty(field.get(data)) ? field.get(data).toString() : "";
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Field getDeclaredField(Class staff, String fieldName) {
        if (ObjectUtils.isEmpty(staff) || ObjectUtils.isEmpty(fieldName)) {
            return null;
        }
        do {
            try {
                Field field = staff.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;

            } catch (Exception e) {
                e.printStackTrace();
            }

        } while ((staff = staff.getSuperclass()) != null);
        return null;
    }
}
