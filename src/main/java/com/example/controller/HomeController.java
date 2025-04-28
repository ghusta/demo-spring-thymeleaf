package com.example.controller;

import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    private static final String SPRING_VERSION = SpringVersion.getVersion();

    @GetMapping("/")
    public String home(Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        model.addAttribute("message", "Bienvenue dans Spring " + SPRING_VERSION + " avec Thymeleaf !");
        model.addAttribute("serverTime", formatter.format(now));

        return "home"; // Affiche home.html
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", defaultValue = "🤡") String name, Model model) {
        model.addAttribute("name", name);

        List<String> myList = List.of("Hello World", "Hello World", "Hello World", "Hello World");
        model.addAttribute("myList", myList);

        return "greeting"; // Affiche greeting.html
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        model.addAttribute("utcNow", Instant.now());
        model.addAttribute("localNow", LocalDateTime.now());

        return "test";
    }

}
