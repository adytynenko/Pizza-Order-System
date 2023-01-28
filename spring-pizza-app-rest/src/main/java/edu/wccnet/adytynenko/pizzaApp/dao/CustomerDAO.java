package edu.wccnet.adytynenko.pizzaApp.dao;

import java.util.List;

import edu.wccnet.adytynenko.pizzaApp.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers(String sort);

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

}
