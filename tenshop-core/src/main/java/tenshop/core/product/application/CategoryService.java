package tenshop.core.product.application;

import static tenshop.core.product.domain.Category.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tenshop.core.product.domain.Category;
import tenshop.core.product.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public void save(String name, Integer parentId) {
		Category parent = null;
		if (parentId != null) {
			parent = categoryRepository.findById(Long.valueOf(parentId))
				.orElseThrow(() -> new IllegalArgumentException("상위 카테고리가 존재하지 않습니다."));
		}

		int depth = parent != null ? parent.getCategoryDepth() + 1 : 0;
		Category category = create(name, parent, depth);

		categoryRepository.save(category);
	}

	public Category findByCategoryId(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
	}
}
