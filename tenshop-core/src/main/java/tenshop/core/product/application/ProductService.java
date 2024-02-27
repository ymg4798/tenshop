package tenshop.core.product.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.core.product.Product;
import tenshop.core.product.domain.Category;
import tenshop.core.product.repository.CategoryRepository;
import tenshop.core.product.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public Product save(String name, Integer price, Integer stock, Long categoryId, String content) {
		Category category = findById(categoryId);

		Product product = Product.create(name, price, stock, category, content);

		return productRepository.save(product);
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
	}
}
