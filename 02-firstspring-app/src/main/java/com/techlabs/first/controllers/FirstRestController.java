package com.techlabs.first.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {

	@GetMapping("/Hello")
	public String sayHello()
	{
		return "HELLO BUDDY";
	}
	
	
	@GetMapping("/Ronnie")
	public String ronnie()
	{
		return "Yeah BUDDY Light weight !!!!!!!!!";
	}
	
}
