package com.poly.lab7.dao;

import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    // 🔍 Bài tìm kiếm DSL (không cần @Query)
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // 📊 Bài báo cáo tồn kho theo loại hàng
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count " +
            "FROM Product o " +
            "GROUP BY o.category " +
            "ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();
}
