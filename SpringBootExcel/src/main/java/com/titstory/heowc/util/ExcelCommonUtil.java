package com.titstory.heowc.util;

import com.titstory.heowc.config.ExcelConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelCommonUtil {

    private Workbook workbook;
    private Map<String, Object> model;
    private HttpServletResponse response;

    public ExcelCommonUtil(Workbook workbook, Map<String, Object> model, HttpServletResponse response) {
        this.workbook = workbook;
        this.model = model;
        this.response = response;
    }

    public void createExcel() {
        String fileName = (String) model.get(ExcelConfig.FILE_NAME);
        List<String> head = (List<String>) model.get(ExcelConfig.HEAD);
        List<List<String>> body = (List<List<String>>) model.get(ExcelConfig.BODY);

        setFileName(response, fileName);

        Sheet sheet = workbook.createSheet();

        createHead(sheet, head);

        createBody(sheet, body);
    }

    private void setFileName(HttpServletResponse response, String fileName) {

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + setFileExtension(fileName) + "\"");
    }

    private String setFileExtension(String fileName) {
        if ( workbook instanceof XSSFWorkbook) {
            fileName += ".xlsx";
        }
        if ( workbook instanceof SXSSFWorkbook) {
            fileName += ".xlsx";
        }

        if ( workbook instanceof HSSFWorkbook) {
            fileName += ".xls";
        }
        return fileName;
    }

    private void createHead(Sheet sheet, List<String> cellList) {
        createRow(sheet, cellList, 0);
    }

    private void createBody(Sheet sheet, List<List<String>> bodyList) {
        int rowSize = bodyList.size();
        for (int i = 0; i < rowSize; i++) {
            createRow(sheet, bodyList.get(i), i + 1);
        }
    }

    private void createRow(Sheet sheet, List<String> cellList, int rowNum) {
        int size = cellList.size();
        Row row = sheet.createRow(rowNum);

        for (int i = 0; i < size; i++) {
            row.createCell(i)
                    .setCellValue(cellList.get(i));
        }
    }
}
