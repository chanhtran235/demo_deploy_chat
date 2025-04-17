package com.example.demo_chat.controller;


import com.example.demo_chat.model.AppUser;
import com.example.demo_chat.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IAppUserService appUserService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "user-profile";
    }
    @GetMapping("")
    public String home(Model model){
        List<AppUser> list = appUserService.findAll();
        model.addAttribute("list", list);
        return "home";
    }
}
