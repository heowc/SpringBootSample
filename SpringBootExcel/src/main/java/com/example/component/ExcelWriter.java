package com.example.component;

import com.example.constant.ExcelConstant;
import eu.bitwalker.useragentutils.Browser;
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
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
		applyFileNameForRequest(mapToFileName());

		applyContentTypeForRequest();

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

    private void applyFileNameForRequest(String fileName) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String encodedFileName = FileNameEncoder.encode(userAgent.getBrowser().getGroup(), fileName);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + appendFileExtension(encodedFileName) + "\"");
    }

    private String appendFileExtension(String fileName) {
        if (workbook instanceof XSSFWorkbook || workbook instanceof SXSSFWorkbook) {
            fileName += ".xlsx";
        }
        if (workbook instanceof HSSFWorkbook) {
            fileName += ".xls";
        }

		return fileName;
	}

	private void applyContentTypeForRequest() {
		if (workbook instanceof XSSFWorkbook || workbook instanceof SXSSFWorkbook) {
			response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		}
		if (workbook instanceof HSSFWorkbook) {
			response.setHeader("Content-Type", "application/vnd.ms-excel");
		}
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

	private enum FileNameEncoder {
		IE(Browser.IE, (it) -> {
			try {
				return URLEncoder.encode(it, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
			} catch (UnsupportedEncodingException e) {
				return it;
			}
		}),
		FIREFOX(Browser.FIREFOX, FileNameEncoder.simpleEncodeFunction),
		OPERA(Browser.OPERA, FileNameEncoder.simpleEncodeFunction),
		CHROME(Browser.CHROME, FileNameEncoder.simpleEncodeFunction),
		UNKNOWN(Browser.UNKNOWN, UnaryOperator.identity());

		private final Browser browser;
		private UnaryOperator<String> encodeOperator;

		private static final UnaryOperator<String> simpleEncodeFunction = it -> new String(it.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

		private static final Map<Browser, Function<String, String>> FILE_NAME_ENCODER_MAP;

		static {
			FILE_NAME_ENCODER_MAP = EnumSet.allOf(FileNameEncoder.class).stream()
					.collect(Collectors.toMap(FileNameEncoder::getBrowser, FileNameEncoder::getEncodeOperator));
		}

		FileNameEncoder(Browser browser, UnaryOperator<String> encodeOperator) {
			this.browser = browser;
			this.encodeOperator = encodeOperator;
		}

		protected Browser getBrowser() {
			return browser;
		}

		protected UnaryOperator<String> getEncodeOperator() {
			return encodeOperator;
		}

		public static String encode(Browser browser, String fileName) {
			return FILE_NAME_ENCODER_MAP.get(browser).apply(fileName);
		}
	}
}