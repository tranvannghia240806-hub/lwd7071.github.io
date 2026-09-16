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

	// Trang quan ly Category (danh sach + them + sua + xoa qua AJAX)
	@GetMapping("/categories")
	public String categories() {
		return "categories/ajax";
	}

	// Trang quan ly Product (can danh sach Category de hien thi dropdown)
	@GetMapping("/products")
	public String products(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "products/ajax";
	}
}
