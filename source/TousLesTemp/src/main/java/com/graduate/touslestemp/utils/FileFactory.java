package com.graduate.touslestemp.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


@Component
public class FileFactory {
    public static String PATH_TEMPLATE = "D:\\221121\\file";
    public static File createFile(String fileName, Workbook workbook) throws Exception{
        workbook = new XSSFWorkbook();
        OutputStream outputStream = new FileOutputStream(PATH_TEMPLATE + fileName);
        workbook.write(outputStream);
        return ResourceUtils.getFile(PATH_TEMPLATE + fileName);
    }
}
