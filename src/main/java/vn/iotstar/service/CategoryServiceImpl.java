package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Optional<Category> findByCategoryName(String name) {
		return categoryRepository.findByCategoryName(name);
	}

	@Override
	public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByCategoryNameContaining(name, pageable);
	}

	@Override
	public Category save(Category entity) {

		// Neu la update (co id) va khong upload icon moi -> giu icon cu
		if (entity.getCategoryId() != null) {
			Optional<Category> opt = findById(entity.getCategoryId());
			if (opt.isPresent() && (entity.getIcon() == null || entity.getIcon().isBlank())) {
				entity.setIcon(opt.get().getIcon());
			}
		}

		return categoryRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}
}
