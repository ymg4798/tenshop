package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.application.CategoryBroker;
import tenshop.api.product.dto.CategoryRegisterParam;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryBroker categoryBroker;

	@PostMapping("/categories")
	public Response register(@RequestBody CategoryRegisterParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.save(param));

		return Response.create(map);
	}

	@PutMapping("/categories/{categoryId}")
	public Response update(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryRegisterParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", categoryBroker.update(categoryId, param));

		return Response.create(map);
	}
}
