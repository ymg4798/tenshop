package tenshop.api.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tenshop.api.product.application.ProductBroker;
import tenshop.api.product.dto.ProductBuyParam;
import tenshop.api.product.dto.ProductRegisterParam;
import tenshop.api.product.dto.ProductSearchCondition;
import tenshop.api.product.dto.ProductUpdateParam;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductBroker productBroker;

	@PostMapping("/product")
	public Response register(@RequestBody ProductRegisterParam param) {
		Map<String, String> map = new HashMap<>();

		map.put("message", productBroker.save(param));

		return Response.create(map);
	}

	@PatchMapping("/product/{id}")
	public Response updateProduct(@PathVariable("id") Long id, @RequestBody ProductUpdateParam param) {
		Map<String, Object> map = new HashMap<>();

		map.put("message", productBroker.update(id, param));

		return Response.create(map);
	}

	@GetMapping("/products")
	public Response searchProducts(ProductSearchCondition condition){
		return Response.create(productBroker.findProductsBySearchCondition(condition));
	}

	@DeleteMapping("/product/{id}")
	public Response deleteProduct(@PathVariable("id") Long id) {
		Map<String, Object> map = new HashMap<>();

		map.put("message", productBroker.delete(id));

		return Response.create(map);
	}

	@PostMapping("/products/buy")
	public Response buyProducts(@RequestBody ProductBuyParam param, @RequestHeader("userId") Long userId) {
		Map<String, Object> map = new HashMap<>();

		map.put("message", productBroker.buy(param, userId));

		return Response.create(map);
	}
}


