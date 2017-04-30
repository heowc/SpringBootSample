package com.titstory.heowc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("download")
public class DownloadExcelController {

    @GetMapping("excel-xls")
    public String xlsView(Model model) {
        return "excelXlsView";
    }

    @GetMapping("excel-xlsx")
    public String xlsxView(Model model) {
        return "excelXlsxView";
    }

    @GetMapping("excel-xlsx-streaming")
    public String xlsxStreamingView(Model model) {
        return "excelXlsxStreamingView";
    }
}
