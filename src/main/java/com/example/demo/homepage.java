package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class homepage {
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("pageName", "HomePage");
        return "hompage";
    }
}