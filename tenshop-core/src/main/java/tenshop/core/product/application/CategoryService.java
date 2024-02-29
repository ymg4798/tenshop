package tenshop.core.product.application;

import static tenshop.core.product.domain.Category.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.core.product.domain.Category;
import tenshop.core.product.repository.CategoryRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public void save(String name, int depth, Category parent, Category... children) {
		Category category = create(name, depth, parent, children);
		categoryRepository.save(category);
	}

	@Transactional
	public void update(Long id, String name) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
		category.update(name);
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
	}
}


