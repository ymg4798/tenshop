package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.application.CategoryBroker;
import tenshop.api.product.dto.CategoryRegisterParam;
import tenshop.api.product.dto.CategoryResponse;
import tenshop.api.product.dto.CategoryUpdateParam;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryBroker categoryBroker;

	@PostMapping("/category")
	public Response register(@RequestBody CategoryRegisterParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.save(param));

		return Response.create(map);
	}

	@PatchMapping("/category/{id}")
	public Response update(@PathVariable("id") Long id, @RequestBody CategoryUpdateParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.update(id, param));

		return Response.create(map);
	}

	@DeleteMapping("/category/{id}")
	public Response delete(@PathVariable("id") Long id) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.delete(id));

		return Response.create(map);
	}

	@GetMapping("/categories")
	public Response select() {
		Map<String, List<CategoryResponse>> map = new HashMap<>();

		List<CategoryResponse> categoriesTree = categoryBroker.select();

		map.put("categories", categoriesTree);

		return Response.create(map);
	}
}


