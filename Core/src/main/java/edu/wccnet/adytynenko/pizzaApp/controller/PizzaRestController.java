package edu.wccnet.adytynenko.pizzaApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.wccnet.adytynenko.pizzaApp.Service.CustomerService;
import edu.wccnet.adytynenko.pizzaApp.Service.OrderService;
import edu.wccnet.adytynenko.pizzaApp.entity.Customer;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;

@RestController
@RequestMapping("/customer/api")
public class PizzaRestController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers(null);
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return customerService.getCustomer(customerId);
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {
		customerService.deleteCustomer(customerId);
	}
	
	@GetMapping("/orders")
	public List<Order> getOrders() {
		return orderService.getOrders(); //
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getOrder(@PathVariable int orderId) {
		return orderService.getOrder(orderId);
	}
	
	@PostMapping("/orders")
	public Order addOrder(@RequestBody Order order) {
		order.setId(0);
		orderService.saveOrder(order);
		return order;
	}
	
	@PutMapping("/orders")
	public Order updateOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
		return order;
	}
	
	@DeleteMapping("/orders/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@ExceptionHandler
	public ResponseEntity<PizzaErrorResponse> handleException(NotFoundException e){
		PizzaErrorResponse error = new PizzaErrorResponse();
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<PizzaErrorResponse> handleException(Exception e){
		PizzaErrorResponse error = new PizzaErrorResponse();
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
