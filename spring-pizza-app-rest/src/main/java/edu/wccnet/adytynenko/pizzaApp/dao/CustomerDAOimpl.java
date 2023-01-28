package edu.wccnet.adytynenko.pizzaApp.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.wccnet.adytynenko.pizzaApp.entity.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(String sort) {
		Session session = sessionFactory.getCurrentSession();
		
		if (sort == null) {
			sort = "lastName";
		}
		
		Query<Customer> query = session.createQuery("from Customer c order by " + sort, 
				Customer.class);		
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();

//		Query query = session.createQuery("delete from Customer where id= :id");
//		query.setParameter("id", id);
//		query.executeUpdate();
		
		Customer customer = session.get(Customer.class, id);
		session.remove(customer);
	}
	
	
}
