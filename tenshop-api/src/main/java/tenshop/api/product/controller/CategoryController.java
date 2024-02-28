package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.application.CategoryBroker;
import tenshop.api.product.dto.CategoryRegisterParam;
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

	@PutMapping("/category/{id}")
	public Response updateCategory(@PathVariable("id") Long id, @RequestBody CategoryUpdateParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.update(id, param));

		return Response.create(map);
	}
}


