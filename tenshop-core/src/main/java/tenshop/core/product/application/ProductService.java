package tenshop.core.product.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.config.page.PageModel;
import tenshop.config.page.PageRequest;
import tenshop.core.product.Product;
import tenshop.core.product.domain.Category;
import tenshop.core.product.repository.ProductRepository;
import tenshop.core.users.Users;
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
	public void update(Long id, String status, String name, String content, Integer price, Integer stock) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		String currentStatus = (status != null) ? status : product.getStatus().getName();
		String currentName = (name != null) ? name : product.getName();
		String currentContent = (content != null) ? content : product.getContent();
		Integer currentStock = (stock != null) ? stock : product.getStock();
		Integer currentPrice = (price != null) ? price : product.getPrice();

		if (currentStock <= 0) {
			currentStatus = "품절";
		}
		product.updateProduct(currentStatus, currentName, currentContent, currentStock, currentPrice);
	}


	public Product findById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
	}

	public PageModel<Product> findProductsBySearchCondition(String name, Long categoryId, Integer minPrice, Integer maxPrice, Integer page) {
		return new PageModel<>(productRepository.findProductsBySearchCondition(
			name, categoryId, minPrice, maxPrice, new PageRequest(page).of()));
	}

	@Transactional
	public void delete(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		productRepository.delete(product);
	}

	@Transactional
	public void buy(Long productId, int quantity, Long userId) {
		Users user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		product.decreaseStock(quantity);
		productRepository.save(product);
	}
}


