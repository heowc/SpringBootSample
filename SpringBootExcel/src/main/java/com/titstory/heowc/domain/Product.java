package com.titstory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private String uniqueId;

    private String name;

    private String comment;

    public Product(Row row) {
        this(row.getCell(0).getStringCellValue(),
            row.getCell(1).getStringCellValue(),
            row.getCell(2).getStringCellValue());
    }
}
