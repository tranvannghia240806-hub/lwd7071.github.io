package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Optional<Product> findByProductName(String name) {
		return productRepository.findByProductName(name);
	}

	@Override
	public Page<Product> findByProductNameContaining(String name, Pageable pageable) {
		return productRepository.findByProductNameContaining(name, pageable);
	}

	@Override
	public Product save(Product entity) {

		if (entity.getProductId() != null) {
			Optional<Product> opt = findById(entity.getProductId());
			if (opt.isPresent() && (entity.getImages() == null || entity.getImages().isBlank())) {
				entity.setImages(opt.get().getImages());
			}
		}

		return productRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}