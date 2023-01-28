package edu.wccnet.adytynenko.pizzaApp.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PizzaService {
	
	public Map<String, String> populateSize() {
		Map<String,String> sizeList = new LinkedHashMap<String,String>();
		sizeList.put("Small","Small");
		sizeList.put("Medium","Medium");
		sizeList.put("Large","Large");
		
		return sizeList;
	}
	
	
	public Map<String, String> populateToppings() {
		Map<String,String> toppingsList = new LinkedHashMap<String,String>();
		toppingsList.put("Salami","Salami");
		toppingsList.put("Mushroom","Mushroom");
		toppingsList.put("Pineapple","Pineapple");
		toppingsList.put("Tomato","Tomato");
		toppingsList.put("Corn","Corn");
		toppingsList.put("Chicken","Chicken");
		toppingsList.put("Ham","Ham");
		toppingsList.put("Peppers","Peppers");
		toppingsList.put("Arugula","Arugula");
		toppingsList.put("Cheese","Cheese");
		
		return toppingsList;
	}
	
	
	public Map<String, String> populateState() {
		Map<String,String> stateList = new LinkedHashMap<String,String>();
		stateList.put("MI","MI");
		stateList.put("IL","IL");
		stateList.put("NY","NY");
		stateList.put("CA","CA");
		stateList.put("TX","TX");
		
		return stateList;
	}

}
