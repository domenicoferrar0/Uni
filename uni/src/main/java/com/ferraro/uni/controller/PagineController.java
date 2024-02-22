package com.ferraro.uni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagineController {

	
	@GetMapping(value = "/form-studenti")
	public String showForm(Model model) {
		return "formStudente";
	}
}
