package com.tistory.heowc.util;

import com.tistory.heowc.domain.Page;

public class PageUtil {

    public static Page of(int pageNo, int recodeSize, long totalSize) {
        Page page = new Page();

        page.setPageNo(pageNo);
        page.setRecodeSize(recodeSize);
        page.setTotalSize(totalSize);

        page.setTop((pageNo - 1) * recodeSize);
        return page;
    }
}
