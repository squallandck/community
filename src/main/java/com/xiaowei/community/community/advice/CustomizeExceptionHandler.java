package com.xiaowei.community.community.advice;

import com.xiaowei.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yxw on 2020/2/15
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model) {
        if (ex instanceof CustomizeException) {
            model.addAttribute("message",ex.getMessage());
        } else {
            model.addAttribute("message", "服务冒烟了！");
        }
        return new ModelAndView("error");
    }
}
