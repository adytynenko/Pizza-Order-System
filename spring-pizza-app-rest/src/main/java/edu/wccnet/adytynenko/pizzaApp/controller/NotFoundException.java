package edu.wccnet.adytynenko.pizzaApp.controller;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String msg) {
		super(msg);
	}
}
