package com.example.website.controller;

import com.example.website.entity.User;
import com.example.website.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        var user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("books", libraryService.getAllBooks()); // Передаем книги в модель
        return "admin";
    }
}