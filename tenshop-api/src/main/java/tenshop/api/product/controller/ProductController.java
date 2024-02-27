package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.application.ProductBroker;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductBroker productBroker;

	@PostMapping("/products")
	public Response register(@RequestBody ProductRegisterParam param){
		Map<String, String> map = new HashMap<>();

		map.put("message", productBroker.save(param));

		return Response.create(map);
	}
}
