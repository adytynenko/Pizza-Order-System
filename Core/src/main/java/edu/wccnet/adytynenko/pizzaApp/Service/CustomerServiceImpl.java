 package edu.wccnet.adytynenko.pizzaApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.wccnet.adytynenko.pizzaApp.controller.NotFoundException;
import edu.wccnet.adytynenko.pizzaApp.dao.CustomerDAO;
import edu.wccnet.adytynenko.pizzaApp.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(String sort) {
		return customerDAO.getCustomers(sort);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		
		Customer customer = customerDAO.getCustomer(id);
		if (customer == null) {
			throw new NotFoundException("customer id not found. Id: " + id);
		}
		
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		
		Customer customer = customerDAO.getCustomer(id);
		if (customer == null) {
			throw new NotFoundException("customer id not found. Id: " + id);
		}
		
		customerDAO.deleteCustomer(id);
	}

	
}
