package vn.iotstar.controllers.api;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.iotstar.entity.Category;
import vn.iotstar.model.Response;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IStorageService;

@Tag(name = "Category API", description = "CRUD API cho bảng Category")
@RestController
@RequestMapping(path = "/api/category")
public class CategoryAPIController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IStorageService storageService;

	// =========================
	// GET ALL
	// =========================
	@GetMapping
	public ResponseEntity<?> getAllCategory() {
		return new ResponseEntity<Response>(
				new Response(true, "Thành công", categoryService.findAll()), HttpStatus.OK);
	}

	// =========================
	// GET BY ID
	// =========================
	@PostMapping(path = "/getCategory")
	public ResponseEntity<?> getCategory(@Validated @RequestParam("id") Long id) {
		Optional<Category> category = categoryService.findById(id);

		if (category.isPresent()) {
			return new ResponseEntity<Response>(
					new Response(true, "Thành công", category.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(
					new Response(false, "Không tìm thấy Category", null), HttpStatus.NOT_FOUND);
		}
	}

	// =========================
	// CREATE
	// =========================
	@PostMapping(path = "/addCategory")
	public ResponseEntity<?> addCategory(
			@Validated @RequestParam("categoryName") String categoryName,
			@RequestParam(value = "icon", required = false) MultipartFile icon) {

		Optional<Category> optCategory = categoryService.findByCategoryName(categoryName);

		if (optCategory.isPresent()) {
			return new ResponseEntity<Response>(
					new Response(false, "Category đã tồn tại trong hệ thống", null), HttpStatus.BAD_REQUEST);
		}

		Category category = new Category();

		if (icon != null && !icon.isEmpty()) {
			String uuString = UUID.randomUUID().toString();
			category.setIcon(storageService.getStorageFilename(icon, uuString));
			storageService.store(icon, category.getIcon());
		}

		category.setCategoryName(categoryName);
		categoryService.save(category);

		return new ResponseEntity<Response>(
				new Response(true, "Thêm thành công", category), HttpStatus.OK);
	}

	// =========================
	// UPDATE
	// =========================
	@PutMapping(path = "/updateCategory")
	public ResponseEntity<?> updateCategory(
			@Validated @RequestParam("categoryId") Long categoryId,
			@Validated @RequestParam("categoryName") String categoryName,
			@RequestParam(value = "icon", required = false) MultipartFile icon) {

		Optional<Category> optCategory = categoryService.findById(categoryId);

		if (optCategory.isEmpty()) {
			return new ResponseEntity<Response>(
					new Response(false, "Không tìm thấy Category", null), HttpStatus.BAD_REQUEST);
		}

		Category category = optCategory.get();

		if (icon != null && !icon.isEmpty()) {
			String uuString = UUID.randomUUID().toString();
			category.setIcon(storageService.getStorageFilename(icon, uuString));
			storageService.store(icon, category.getIcon());
		}

		category.setCategoryName(categoryName);
		categoryService.save(category);

		return new ResponseEntity<Response>(
				new Response(true, "Cập nhật thành công", category), HttpStatus.OK);
	}

	// =========================
	// DELETE
	// =========================
	@DeleteMapping(path = "/deleteCategory")
	public ResponseEntity<?> deleteCategory(@Validated @RequestParam("categoryId") Long categoryId) {

		Optional<Category> optCategory = categoryService.findById(categoryId);

		if (optCategory.isEmpty()) {
			return new ResponseEntity<Response>(
					new Response(false, "Không tìm thấy Category", null), HttpStatus.BAD_REQUEST);
		}

		categoryService.deleteById(categoryId);

		return new ResponseEntity<Response>(
				new Response(true, "Xóa thành công", optCategory.get()), HttpStatus.OK);
	}
}
