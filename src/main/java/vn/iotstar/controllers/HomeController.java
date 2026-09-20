package vn.iotstar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import vn.iotstar.service.ICategoryService;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final ICategoryService categoryService;

	@GetMapping("/")
	public String home() {
		return "redirect:/categories";
	}

	@GetMapping("/categories")
	public String categories() {
		return "categories/ajax";
	}

	@GetMapping("/products")
	public String products(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "products/ajax";
	}
}