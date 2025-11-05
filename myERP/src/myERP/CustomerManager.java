package myERP;
import java.util.*;

public class CustomerManager {
	private Map<UUID,Customer> customers;
	
	public CustomerManager() {
		this.customers=new HashMap<>();
	}
	
	public boolean addCustomer(Customer c) {
	    if (c == null) {
	        return false;
	    }

	    if (c.getId() == null) {
	        return false;
	    }

	    if (customers.containsKey(c.getId())) {
	        return false;
	    }

	    customers.put(c.getId(), c);
	    return true;
	}
	
	public boolean removeCustomer(UUID id) {
		if(id==null) {
			return false;
		}
		
		if(customers.remove(id)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Customer getCustomerById(UUID Id) {
		if(Id==null) {
			return null;
		}
		
		return customers.get(Id);
	}
	
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> getCustomers=new ArrayList<>();
		
		for(Customer c:customers.values()) {
			getCustomers.add(c);
		}
		
		return getCustomers;
	}
	
	public boolean activateCustomer(UUID id) {
		if(id==null) {
			return false;
		}
		
		Customer customer=customers.get(id);
		
		if(customer!=null) {
			customer.activate();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deactivateCustomer(UUID id) {
		if(id==null) {
			return false;
		}
		
		Customer customer=customers.get(id);
		
		if(customer!=null) {
			customer.deactivate();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean updateCustomerContact(UUID id,String newEmail,String newPhone) {
		if(id==null) {
			return false;
		}
		
		Customer customer=customers.get(id);
		
		if(customer!=null) {
			return customer.updateContactInfo(newEmail, newPhone);
		}
		return false;
	}
	
	public boolean updateCustomerAddress(UUID id,String newAddress) {
		
		if(id==null) {
			return false;
		}
		
		Customer customer=customers.get(id);
		
		if(customer!=null) {
			return customer.updateAddress(newAddress);

		}
		return false;
	}
}