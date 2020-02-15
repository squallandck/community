package com.xiaowei.community.community.controller;

import com.xiaowei.community.community.dto.QuestionDto;
import com.xiaowei.community.community.mapper.QuestionMapper;
import com.xiaowei.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by yxw on 2020/2/13
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDto questionDto = questionService.getById(id);
        //增加累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        return "question";
    }
}
