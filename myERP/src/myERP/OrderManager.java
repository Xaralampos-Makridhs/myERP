package myERP;

import java.util.*;

public class OrderManager {
    private Map<UUID, Order> orders;

    public OrderManager() {
        this.orders = new HashMap<>();
    }

   
    public boolean addOrder(Order o) {
        if (o == null || o.getId() == null || orders.containsKey(o.getId())) {
            return false;
        }
        orders.put(o.getId(), o);
        return true;
    }

   
    public boolean removeOrder(UUID id) {
        if (id == null) return false;
        return orders.remove(id) != null;
    }

   
    public Order getOrderById(UUID id) {
        if (id == null) return null;
        return orders.get(id);
    }

    
    public ArrayList<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

   
    public boolean completeOrder(UUID id) {
        if (id == null) return false;
        Order order = orders.get(id);
        if (order != null) {
            order.completeOrder();
            return true;
        }
        return false;
    }

   
    public boolean cancelOrder(UUID id) {
        if (id == null) return false;
        Order order = orders.get(id);
        if (order != null) {
            order.cancelOrder();
            return true;
        }
        return false;
    }

    
    public double calculateOrderTotal(UUID id) {
        if (id == null) return 0;
        Order order = orders.get(id);
        if (order != null) {
            return order.calculateTotal();
        }
        return 0;
    }
}
