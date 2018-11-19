package com.example;

import com.example.component.ExcelReader;
import com.example.domain.Product;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootExcelApplicationTests {

	@Autowired
	ExcelReader excelReader;

	@Test
	public void test_readExcel() throws IOException, InvalidFormatException {
		File xlsxFile = new File("{path}\\test.xlsx");

		excelReader
				.readFileToList(new MockMultipartFile("test.xlsx", new FileInputStream(xlsxFile)),
								Product::from)
				.forEach(System.out::println);
	}
}
