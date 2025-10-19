package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rect")
public class RectangleController {

    @Autowired
    private HttpServletRequest request;

    // Hiển thị form nhập
    @GetMapping("/form")
    public String form() {
        return "demo/rectangle"; // => templates/demo/rectangle.html
    }

    // Xử lý tính toán
    @PostMapping("/calculate")
    public String calculate(Model model) {
        try {
            double width = Double.parseDouble(request.getParameter("width"));
            double height = Double.parseDouble(request.getParameter("height"));

            double area = width * height;
            double perimeter = 2 * (width + height);

            model.addAttribute("width", width);
            model.addAttribute("height", height);
            model.addAttribute("area", area);
            model.addAttribute("perimeter", perimeter);
        } catch (Exception e) {
            model.addAttribute("error", "Vui lòng nhập số hợp lệ!");
        }

        return "demo/rectangle-result"; // => templates/demo/rectangle-result.html
    }
}
