package tenshop.order.controller;


import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;
import tenshop.order.application.OrderBroker;
import tenshop.order.dto.OrderRegisterParam;

@ResponseAnnotation
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderBroker orderBroker;

    @PostMapping("/order")
    public Response createOrder(@RequestBody OrderRegisterParam registerParam) {

        Long memberId = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message",  orderBroker.createOrder(memberId,registerParam));
        return Response.create(response);

    }


}
