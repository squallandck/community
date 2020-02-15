package com.xiaowei.community.community.controller;

import com.xiaowei.community.community.dto.PaginationDto;
import com.xiaowei.community.community.model.User;
import com.xiaowei.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yxw on 2020/2/12
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "2") Integer size,
                          Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }
        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDto paginationDto = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDto);
        return "profile";
    }

}
