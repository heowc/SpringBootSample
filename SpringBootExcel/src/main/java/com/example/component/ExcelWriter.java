package com.example.component;

import com.example.constant.ExcelConstant;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

	private Workbook workbook;
	private Map<String, Object> model;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ExcelWriter(Workbook workbook, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		this.workbook = workbook;
		this.model = model;
		this.request = request;
		this.response = response;
	}

	public void create() {
		setFileName(mapToFileName());

		Sheet sheet = workbook.createSheet();

		createHead(sheet, mapToHeadList());

		createBody(sheet, mapToBodyList());
	}

	private String mapToFileName() {
		return (String) model.get(ExcelConstant.FILE_NAME);
	}

	private List<String> mapToHeadList() {
		return (List<String>) model.get(ExcelConstant.HEAD);
	}

	private List<List<String>> mapToBodyList() {
		return (List<List<String>>) model.get(ExcelConstant.BODY);
	}

    private void setFileName(String fileName) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String docName = null;

        try {
            switch (userAgent.getBrowser().getGroup()) {
                case IE:
                    docName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
                    break;
                case FIREFOX:
                case OPERA:
                case CHROME:
                    docName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
                    break;
                default:
                    docName = fileName;
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            docName = fileName;
        }

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + getFileExtension(docName) + "\"");
    }

    private String getFileExtension(String fileName) {
        if (workbook instanceof XSSFWorkbook || workbook instanceof SXSSFWorkbook) {
            fileName += ".xlsx";
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
        if (workbook instanceof HSSFWorkbook) {
            fileName += ".xls";
            response.setHeader("Content-Type", "application/vnd.ms-excel");
        }

		return fileName;
	}

	private void createHead(Sheet sheet, List<String> headList) {
		createRow(sheet, headList, 0);
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
			row.createCell(i).setCellValue(cellList.get(i));
		}
	}
}