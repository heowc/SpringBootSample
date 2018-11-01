package com.tistory.heowc.web;

import com.tistory.heowc.domain.NoticeWithPage;
import com.tistory.heowc.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService service;

    @GetMapping
    public NoticeWithPage getNotices(@RequestParam(required = false, defaultValue = "1") Integer page) {
        if (page > 0) {
            return service.getNotices(page);
        } else {
            throw new IllegalArgumentException("Page must be 0 or greater.");
        }
    }
}
