package edu.wccnet.adytynenko.pizzaApp.Service;

import java.util.List;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;

public interface OrderService {

	public List<Order> getOrders(int customerId);

	public void saveOrder(Order order);

	public Order getOrder(int id);
	
	public void deleteOrder(int id);

	public List<Order> getOrders();


}
