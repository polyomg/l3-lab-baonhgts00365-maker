package com.poly.lab5.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /**
     * Đọc cookie từ request
     */
    public Cookie get(String name) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * Đọc giá trị của cookie
     */
    public String getValue(String name) {
        Cookie cookie = get(name);
        return (cookie != null) ? cookie.getValue() : "";
    }

    /**
     * Tạo và gửi cookie về client
     */
    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(hours * 60 * 60);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * Xóa cookie khỏi client
     */
    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // thời gian sống = 0 ⇒ xóa cookie
        response.addCookie(cookie);
    }
}
