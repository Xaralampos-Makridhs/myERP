package myERP;

import java.time.LocalDateTime;
import java.util.UUID;



public class Customer {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String companyName;
	private String vatNumber;
	private String address;

	private CustomerStatus status;
	private String notes;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Customer(UUID id, String firstName, String lastName, String email, String phoneNumber, String companyName,
			String vatNumber, String address, String notes) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
		this.vatNumber = vatNumber;
		this.address = address;
		this.notes = notes;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	
	

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public String getFullName() {
		return "\nFull Name: "+this.firstName+" "+this.lastName;
	}

	@Override
	public String toString() {
	    return "Customer{" +
	            "id=" + id +
	            ", First Name=" + firstName +
	            ", LastName=" + lastName +
	            ", Email=" + email +
	            ", Phone Number=" + phoneNumber +
	            ", Company Name=" + companyName +
	            ", Vat Number=" + vatNumber +
	            ", Address=" + address +
	            ", Status=" + status +
	            ", Notes=" + notes +
	            ", Created At=" + createdAt +
	            ", Updated At=" + updatedAt +
	            '}';
	}
	
	public void activate() {
		this.status=CustomerStatus.ACTIVE;
		this.updatedAt=LocalDateTime.now();
	}
	
	public void deactivate() {
		this.status=CustomerStatus.INACTIVE;
		this.updatedAt=LocalDateTime.now();
	}
	
	public boolean isActive() {
		if(this.status==CustomerStatus.ACTIVE) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean updateContactInfo(String newEmail, String newPhoneNumber) {
	    boolean updated = false;

	    
	    if (newEmail != null) {
	        if (newEmail.contains("@") && newEmail.contains(".")) {
	            this.email = newEmail;
	            updated = true;
	        } else {
	            return false;
	        }
	    }

	    
	    if (newPhoneNumber != null) {
	        this.phoneNumber = newPhoneNumber;
	        updated = true;
	    }

	    
	    if (updated) {
	        this.updatedAt = LocalDateTime.now();
	    }

	    return updated;
	}
	
	public boolean updateAddress(String newAddress) {
	    boolean updated = false;

	    if (newAddress != null && !newAddress.isEmpty()) {
	        this.address = newAddress;
	        this.updatedAt = LocalDateTime.now();
	        updated = true;
	    }

	    return updated;
	}
}
