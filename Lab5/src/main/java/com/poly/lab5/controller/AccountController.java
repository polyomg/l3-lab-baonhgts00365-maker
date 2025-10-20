package com.poly.lab5.controller;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    /**
     * Hiển thị form đăng nhập
     */
    @GetMapping("/account/login")
    public String login1(Model model) {
        // Đọc cookie nếu có
        String savedUser = cookieService.getValue("user");
        model.addAttribute("savedUser", savedUser);
        return "/account/login";
    }

    /**
     * Xử lý form đăng nhập
     */
    @PostMapping("/account/login")
    public String login2(Model model) {
        // Đọc tham số từ form
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        // Kiểm tra đăng nhập
        if (username.equals("poly") && password.equals("123")) {
            // Lưu username vào session
            sessionService.set("username", username);

            // Xử lý ghi nhớ tài khoản
            if (remember) {
                cookieService.add("user", username, 24 * 10); // 10 ngày (240 giờ)
            } else {
                cookieService.remove("user");
            }

            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Sai username hoặc password!");
        }

        model.addAttribute("savedUser", username);
        return "/account/login";
    }
}
