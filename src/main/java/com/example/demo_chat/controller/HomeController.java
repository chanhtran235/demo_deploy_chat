package com.example.demo_chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
