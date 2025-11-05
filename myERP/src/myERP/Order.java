package myERP;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
	private UUID id;
	private Customer customer;
	private Map<Product, Integer> items;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private OrderStatus status;
	
	public Order(UUID id, Customer customer, Map<Product, Integer> items) {
		this.id = id;
		this.customer = customer;
		this.items = new HashMap<>();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Product, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Product, Integer> items) {
		this.items = items;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public boolean addItem(Product p, int quantity) {
		if(p!=null && quantity > 0) {
			items.put(p, items.getOrDefault(p, 0)+quantity);
			this.updatedAt=LocalDateTime.now();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeItem(Product p) {
		if(p!=null && items.containsKey(p)) {
			items.remove(p);
			this.updatedAt=LocalDateTime.now();
			return true;
		}else {
			return false;
		}
	}
	
    public boolean updateQuantity(Product product, int quantity) {
        if (product == null || quantity <= 0 || !items.containsKey(product)) return false;

        items.put(product, quantity);
        updatedAt = LocalDateTime.now();
        return true;
    }
    
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
	
    public void completeOrder() {
        this.status = OrderStatus.COMPLETED;
        this.updatedAt = LocalDateTime.now();
    }
	
    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== Order =====\n");
        sb.append("Order ID: ").append(id).append("\n");
        sb.append("Customer: ").append(customer.getFullName()).append("\n");
        sb.append("Status: ").append(status).append("\n\n");


        sb.append("Items:\n");
        if (items.isEmpty()) {
            sb.append("  (No items in this order)\n");
        } else {
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product p = entry.getKey();
                int qty = entry.getValue();
                sb.append("  - ").append(p.getName())
                  .append(" x ").append(qty)
                  .append(" @ ").append(p.getPrice())
                  .append(" each\n");
            }
        }


        sb.append("\nTotal: ").append(calculateTotal()).append("\n");


        sb.append("Created At: ").append(createdAt).append("\n");
        sb.append("Updated At: ").append(updatedAt).append("\n");
        sb.append("==================");

        return sb.toString();
    }

	

}
