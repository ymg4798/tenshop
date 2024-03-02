package tenshop.core.product.application;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageRequest;
import tenshop.core.product.Product;
import tenshop.core.product.domain.Category;
import tenshop.core.product.repository.ProductRepository;
import tenshop.core.users.repository.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Transactional
	public void save(String status, int stock, int price, String name, String content, Category category) {
		Product product = Product.create(status, stock, price, name, content, category);
		productRepository.save(product);
	}

	@Transactional
	public void update(Long id, String content, int stock) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
		product.updateProduct(content, stock);
	}
	@Transactional
	public void updateStatus(Long id, String status) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
		product.statusUpdate(status);
	}

	public Product findById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
	}

	public PageModel<Product> findAllBySearchCondition(String name, Long categoryId, Integer minPrice, Integer maxPrice, Integer page) {
		Page<Product> allBySearchCondition =
			productRepository.findAllBySearchCondition(name, categoryId, minPrice, maxPrice, new PageRequest(page).of());
		return new PageModel<>(allBySearchCondition);
	}
}


