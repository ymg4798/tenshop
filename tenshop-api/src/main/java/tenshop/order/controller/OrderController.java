package tenshop.order.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;
import tenshop.order.application.OrderBroker;
import tenshop.order.dto.OrderRegisterParam;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderBroker orderBroker;

    @PostMapping("/order")
    public Response register(@RequestBody OrderRegisterParam param) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.register(param));

        return Response.create(response);
    }
}


