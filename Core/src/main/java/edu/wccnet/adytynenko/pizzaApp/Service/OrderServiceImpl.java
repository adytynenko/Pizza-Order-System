 package edu.wccnet.adytynenko.pizzaApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.wccnet.adytynenko.pizzaApp.controller.NotFoundException;
import edu.wccnet.adytynenko.pizzaApp.dao.OrderDAO;
import edu.wccnet.adytynenko.pizzaApp.entity.Customer;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	@Transactional
	public List<Order> getOrders(int customerId) {
		return orderDAO.getOrders(customerId);
	}
	
	@Override
	@Transactional
	public List<Order> getOrders() {
		return orderDAO.getOrders();
	}

	@Transactional
	public void saveOrder(Order order) {
		orderDAO.saveOrder(order);
	}

	@Transactional
	public Order getOrder(int id) {
		
		Order order = orderDAO.getOrder(id);
		if (order == null) {
			throw new NotFoundException("order id not found. Id: " + id);
		}
		
		return orderDAO.getOrder(id);
	}

	@Transactional
	public void deleteOrder(int id) {
		
		Order order = orderDAO.getOrder(id);
		if (order == null) {
			throw new NotFoundException("order id not found. Id: " + id);
		}
		
		orderDAO.deleteOrder(id);
	}

}
