package edu.wccnet.adytynenko.pizzaApp.dao;

import java.util.List;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;

public interface OrderDAO {

	//public List<Order> getOrders();

	public void saveOrder(Order order);

	public Order getOrder(int id);
	
	public void deleteOrder(int id);

	public List<Order> getOrders(int customerId);

	public List<Order> getOrders();

}
