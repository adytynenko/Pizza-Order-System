package edu.wccnet.adytynenko.pizzaApp.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.wccnet.adytynenko.pizzaApp.entity.Customer;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;

@Repository
public class OrderDAOimpl implements OrderDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Order> getOrders(int customerId) {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Customer> query = session.createQuery("from Customer c where id= :customerId", 
//				Customer.class);
//		query.setParameter("customerId", customerId);
//		Customer customer = query.getSingleResult();
//		return customer.getOrders();
		
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		Query<Order> query = session.createQuery("from Order o join fetch o.pizzas where o.customer = :customer",
				Order.class);
		query.setParameter("customer", customer);
		List<Order> orders = query.getResultList();
		return orders;

	}
	
	@Override
	public List<Order> getOrders() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order o join fetch o.pizzas", 
				Order.class);		
		return query.getResultList();
	}
	
	@Override
	public void saveOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
	}

	@Override
	public Order getOrder(int id) {
		Session session = sessionFactory.getCurrentSession();
//		return session.get(Order.class, id);
		
		
		Query<Order> query = session.createQuery("from Order o join fetch o.pizzas where o.id = :orderId", 
				Order.class);
		query.setParameter("orderId", id);
		Order order = query.getSingleResult();
		return order;

		
	}

	@Override
	public void deleteOrder(int id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = session.get(Order.class, id);
		session.remove(order);
		
	}

}
