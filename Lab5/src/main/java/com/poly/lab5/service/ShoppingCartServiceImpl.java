package com.poly.lab5.service;

import com.poly.lab5.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    // Map chứa các mặt hàng (id, Item)
    Map<Integer, Item> map = new HashMap<>();

    /**
     * Thêm mặt hàng vào giỏ hoặc tăng số lượng nếu đã có
     */
    @Override
    public Item add(Integer id) {
        Item item = map.get(id);
        if (item == null) {
            // Giả lập dữ liệu sản phẩm (thực tế sẽ lấy từ database)
            switch (id) {
                case 1 -> item = new Item(1, "Sản phẩm A", 100.0, 1);
                case 2 -> item = new Item(2, "Sản phẩm B", 200.0, 1);
                case 3 -> item = new Item(3, "Sản phẩm C", 300.0, 1);
                default -> item = new Item(id, "Mặt hàng #" + id, 50.0, 1);
            }
            map.put(id, item);
        } else {
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    /**
     * Xóa mặt hàng khỏi giỏ
     */
    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    /**
     * Cập nhật số lượng mới cho mặt hàng
     */
    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) {
            item.setQty(qty);
            if (qty <= 0) {
                map.remove(id);
            }
        }
        return item;
    }

    /**
     * Xóa sạch giỏ hàng
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * Lấy tất cả mặt hàng trong giỏ
     */
    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    /**
     * Tổng số lượng mặt hàng
     */
    @Override
    public int getCount() {
        return map.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    /**
     * Tổng tiền giỏ hàng
     */
    @Override
    public double getAmount() {
        return map.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
