package myERP;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
	private UUID id;
	private String name;
	private String description;
	private double price;
	private int stock;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private ProductStatus status;
	
	
	public Product(UUID id, String name, String description, double price, int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
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


	public ProductStatus getStatus() {
		return status;
	}


	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	    return "Product{" +
	            "id=" + id +
	            ", Name='" + name + '\'' +
	            ", Description='" + description + '\'' +
	            ", Price=" + price +
	            ", Created At=" + createdAt +
	            ", Updated At=" + updatedAt +
	            '}';
	}
	
	public String getFullName() {
		return "Product: "+name+"\n"+"Description: "+description+"\n";
	}
	
	public boolean updatePrice(double newPrice) {
		if(newPrice <= 0) {
			return false;
		}else {
			this.price=newPrice;
			this.updatedAt=LocalDateTime.now();
			return true;
		}
	}
	
	public boolean updateStock(int newStock) {
		if(newStock <= 0) {
			return false;
		}else {
			this.stock=newStock;
			this.updatedAt=LocalDateTime.now();
			return true;
		}
	}
	
	public void activate() {
		this.status=ProductStatus.ACTIVE;
		this.updatedAt=LocalDateTime.now();
	}
	
	public void discontinue() {
		this.status=ProductStatus.DISCONTINUED;
		this.updatedAt=LocalDateTime.now();
	}
	
	public boolean applyDiscount(int discount) {
		if(discount > 0 && discount <100) {
			double newPrice=this.price*(1-discount/100.0);
			this.price=newPrice;
			this.updatedAt=LocalDateTime.now();
			return true;
		}else {
			return false;
		}
	}
	
	
	
	

	
	
	
	
}
