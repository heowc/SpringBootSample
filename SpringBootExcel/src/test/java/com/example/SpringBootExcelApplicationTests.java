package com.example;

import com.example.component.ExcelReader;
import com.example.domain.Product;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class SpringBootExcelApplicationTests {

    @Autowired
    ExcelReader excelReader;

    @Test
    void test_readExcel() throws IOException, InvalidFormatException {
        File xlsxFile = ResourceUtils.getFile("classpath:test.xlsx");

        final String name = "test.xlsx";
        excelReader
                .readFileToList(new MockMultipartFile(name, name, MediaType.APPLICATION_XHTML_XML_VALUE, new FileInputStream(xlsxFile)),
                        Product::from)
                .forEach(System.out::println);
    }
}
