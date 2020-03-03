package com.example;

import com.example.component.ExcelReader;
import com.example.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

@SpringBootTest
class SpringBootExcelApplicationTests {

    @Autowired
    ExcelReader excelReader;

    @Test
    void test_readExcel() throws Exception {
        final File xlsxFile = ResourceUtils.getFile("classpath:test.xlsx");
        final MockMultipartFile multipartFile = new MockMultipartFile(xlsxFile.getName(), xlsxFile.getName(), MediaType.APPLICATION_XHTML_XML_VALUE, new FileInputStream(xlsxFile));

        final List<Product> actual = excelReader.readFileToList(multipartFile, Product::from);

        assertThat(actual).size().isEqualTo(3);
        assertThat(actual).satisfies(p -> {
            assertThat(p.getUniqueId()).isEqualTo("A");
            assertThat(p.getName()).isEqualTo("a");
            assertThat(p.getComment()).isEqualTo("가");
        }, atIndex(0));

        assertThat(actual).satisfies(p -> {
            assertThat(p.getUniqueId()).isEqualTo("B");
            assertThat(p.getName()).isEqualTo("b");
            assertThat(p.getComment()).isEqualTo("나");
        }, atIndex(1));

        assertThat(actual).satisfies(p -> {
            assertThat(p.getUniqueId()).isEqualTo("C");
            assertThat(p.getName()).isEqualTo("c");
            assertThat(p.getComment()).isEqualTo("다");
        }, atIndex(2));
    }
}
