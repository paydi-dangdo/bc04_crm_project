package com.cybersoft.crm04.controller;

import com.cybersoft.crm04.entity.UsersEntity;
import com.cybersoft.crm04.repository.UsersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public String login(HttpServletRequest request, Model model) {
//        List<UsersEntity> listUsers = usersRepository.findByEmailAndPassword("nguyenvana@gmail.com", "!@#$%^&*");
//        for (UsersEntity data : listUsers) {
//            System.out.println(data.getEmail());
//        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();

                String cookie_email_value;
                String cookie_password_value;

                if (name.equals("email")) {
                    cookie_email_value = value;
                    model.addAttribute("cookie_email_value", cookie_email_value);
                }

                if (name.equals("password")) {
                    cookie_password_value = value;
                    model.addAttribute("cookie_password_value", cookie_password_value);
                }
            }
        }
        return "login";
    }

    @PostMapping("")
    public String procressLogin(Model model, @RequestParam String email, @RequestParam String password, @RequestParam boolean remember, HttpServletResponse response) {

        boolean isSuccess = false;

        List<UsersEntity> listUsers = usersRepository.findByEmailAndPassword(email, password);

        if (listUsers.size() > 0) {
//            if (remember == true) {
//                Cookie u_email = new Cookie("email", email);
//                Cookie u_password = new Cookie("password", password);
//
//                response.addCookie(u_email);
//                response.addCookie(u_password);
//            }

            return "login";

        } else {
            System.out.println("Đăng nhập thất bại");
        }

        model.addAttribute("isSuccess", isSuccess);
        return "login";
    }
}
