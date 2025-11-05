package myERP;

import java.util.*;

public class ProductManager {
    private Map<UUID, Product> products;

    public ProductManager() {
        this.products = new HashMap<>();
    }

    public boolean addProduct(Product p) {
        if (p == null || p.getId() == null || products.containsKey(p.getId())) {
            return false;
        }
        products.put(p.getId(), p);
        return true;
    }

    public boolean removeProduct(UUID id) {
        if (id == null) return false;
        return products.remove(id) != null;
    }

    public Product getProductById(UUID id) {
        if (id == null) return null;
        return products.get(id);
    }

    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean updateProductPrice(UUID id, double newPrice) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            return product.updatePrice(newPrice);
        }
        return false;
    }

    public boolean updateProductStock(UUID id, int newStock) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            return product.updateStock(newStock);
        }
        return false;
    }

    public boolean updateProductName(UUID id, String newName) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            product.setName(newName);
            return true;
        }
        return false;
    }

    public boolean applyDiscount(UUID id, int discount) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            return product.applyDiscount(discount);
        }
        return false;
    }

    public boolean activateProduct(UUID id) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            product.activate();
            return true;
        }
        return false;
    }

    public boolean discontinueProduct(UUID id) {
        if (id == null) return false;
        Product product = products.get(id);
        if (product != null) {
            product.discontinue();
            return true;
        }
        return false;
    }
}
