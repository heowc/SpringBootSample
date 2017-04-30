package com.titstory.heowc.domain;

import org.apache.poi.ss.usermodel.Row;

public class ProductBuilder {

    public Product toProduct(Row row) {
        return new Product(row.getCell(0).getStringCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue());
    }
}
