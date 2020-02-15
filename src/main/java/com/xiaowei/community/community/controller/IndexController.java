package com.xiaowei.community.community.controller;

import com.xiaowei.community.community.dto.PaginationDto;
import com.xiaowei.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "2") Integer size) {
        PaginationDto pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }


}
