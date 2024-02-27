package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
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
}
