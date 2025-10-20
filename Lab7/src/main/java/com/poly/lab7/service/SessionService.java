package com.poly.lab7.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    HttpSession session;

    // Lấy giá trị từ session
    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        T value = (T) session.getAttribute(name);
        return value != null ? value : defaultValue;
    }

    // Ghi giá trị vào session
    public <T> void set(String name, T value) {
        session.setAttribute(name, value);
    }

    // Xóa giá trị trong session
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
