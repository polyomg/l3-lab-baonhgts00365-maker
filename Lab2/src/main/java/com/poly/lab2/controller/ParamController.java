package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping(value = "/save/{x}", method = RequestMethod.POST)
    public String save(
            @PathVariable("x") String x,     // lấy từ URL
            @RequestParam("y") String y,     // lấy từ input form
            Model model) {

        // đưa dữ liệu ra view
        model.addAttribute("x", x);
        model.addAttribute("y", y);

        return "form";  // trả về form.html
    }
}
