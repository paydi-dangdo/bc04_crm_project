package com.cybersoft.crm04.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/cookie")
public class DemoCookieController {

    @GetMapping("")
    public String createCookie(HttpServletResponse reponse, HttpServletRequest request) {
        // khởi tạo cookie
//        Cookie cookie = new Cookie("hello", "lookcc");
//        Cookie cookie1 = new Cookie("username", URLEncoder.encode("Nguyễn Văn A", StandardCharsets.UTF_8));
//
//        //server bắt client tạo cookie
//        reponse.addCookie(cookie);
//        reponse.addCookie(cookie1);
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();

            if (name.equals("hello")) {
                System.out.println("Kiểm tra cookie. Name: " + name + " - value: " + value);
            }

        }

        return "login";
    }
}
