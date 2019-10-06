package com.dandi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

	@GetMapping(value="/message")
	public String message() {
		return "Hello";
	}
}
