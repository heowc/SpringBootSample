package com.example.component;

import com.example.constant.ExcelConstant;
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
		setFileName(response, mapToFileName());

		Sheet sheet = workbook.createSheet();

		createHead(sheet, mapToHeadList());

		createBody(sheet, mapToBodyList());
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");

		if (header.contains("MSIE")) {
			return "MSIE";
		} else if(header.contains("Trident")) {
			return "MSIE11";
		} else if(header.contains("Chrome")) {
			return "Chrome";
		} else if(header.contains("Opera")) {
			return "Opera";
		}

		return "Firefox";
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

	private void setFileName(HttpServletResponse response, String fileName) {
		String header = getBrowser(request);

		try {
			if (header.contains("MSIE")) {
				String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
				response.setHeader("Content-Disposition", "attachment;filename=" + getFileExtension(response, docName)  + ";");
			} else if (header.contains("Firefox")) {
				String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileExtension(response, docName)  + "\"");
			} else if (header.contains("Opera")) {
				String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileExtension(response, docName)  + "\"");
			} else if (header.contains("Chrome")) {
				String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileExtension(response, docName)  + "\"");
			}
		} catch (UnsupportedEncodingException e) {
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + getFileExtension(response, fileName) + "\"");
		}
	}

	private String getFileExtension(HttpServletResponse response, String fileName) {
		if (workbook instanceof XSSFWorkbook) {
			fileName += ".xlsx";
			response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		}
		if (workbook instanceof SXSSFWorkbook) {
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