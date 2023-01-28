package edu.wccnet.adytynenko.pizzaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.wccnet.adytynenko.pizzaApp.Service.CustomerService;
import edu.wccnet.adytynenko.pizzaApp.Service.OrderService;
import edu.wccnet.adytynenko.pizzaApp.entity.Address;
import edu.wccnet.adytynenko.pizzaApp.entity.Customer;
import edu.wccnet.adytynenko.pizzaApp.entity.Order;
import edu.wccnet.adytynenko.pizzaApp.entity.Pizza;
import edu.wccnet.adytynenko.pizzaApp.entity.PizzaService;

@Controller
@RequestMapping("/customer")
public class MainController {
	private Order order;
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.getCustomers(null));
		
		return "list-customer";
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "add-customer";
	}
	
	@PostMapping("/processCustomer")
	public String processCustomer(@ModelAttribute("customer") Customer theCustomer) {
		System.out.println(theCustomer);
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/updateCustomer")
	public String updateCustomer(Model model, @RequestParam("customerId") int id) {
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		
		return "add-customer";
	}

	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/orderHistory")
	public String listOrders(Model model, @RequestParam("customerId") int id) {
		model.addAttribute("orders", orderService.getOrders(id));
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customerName", customer.getFirstName() + " " + customer.getLastName());
		model.addAttribute("customerId", customer.getId());
		
		return "list-order";
	}
		
	@RequestMapping("/orderPizza")
	public ModelAndView orderPizza(Model model, @RequestParam("customerId") int id) {
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customerName", customer.getFirstName() + " " + customer.getLastName());
		
		if (order == null) {
			order = new Order(customer);
		}
		
		return new ModelAndView("order-pizza", "newPizza", new Pizza());
		
	}
		
	@RequestMapping("/addPizza")
	public String addPizza(Model model, @ModelAttribute("newPizza") Pizza pizza) {

		//System.out.println(pizza.getSize());
		
		order.add(pizza);

		//System.out.println(order.getCustomer());
		
//		for (Pizza eachPizza : order.getPizzas()) {
//			System.out.println(eachPizza);
//		}
		
		model.addAttribute("order", order);
		return "confirmation";
		
	}
	
	@PostMapping("/deletePizza")
	public String deletePizza(@RequestParam("pizzaId") int id, Model model) {
		order.getPizzas().remove(id-1);
		model.addAttribute("order", order);
		return "confirmation";
	}
		
		
	@RequestMapping("/placeOrder")
	public String placeOrder(@RequestParam("customerId") int id, RedirectAttributes redirectAttributes) {	
		orderService.saveOrder(order);
		order = null;
		redirectAttributes.addAttribute("customerId", id);
		return "redirect:/customer/orderHistory";
		
	}
	
	@PostMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("orderId") int id, RedirectAttributes redirectAttributes) {
		int customerId = orderService.getOrder(id).getCustomer().getId();
		orderService.deleteOrder(id);
		redirectAttributes.addAttribute("customerId", customerId);
		return "redirect:/customer/orderHistory";
	}
	
	@ModelAttribute
	public void populateFormWithData(Model model) {
		model.addAttribute("sizeList", pizzaService.populateSize());
		model.addAttribute("toppingsList", pizzaService.populateToppings());
		model.addAttribute("stateList", pizzaService.populateState());

	}
		
}
