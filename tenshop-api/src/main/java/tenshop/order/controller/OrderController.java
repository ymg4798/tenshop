package tenshop.order.controller;


import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tenshop.config.annotation.ResponseAnnotation;
import tenshop.config.annotation.aspect.dto.Response;
import tenshop.order.application.OrderBroker;
import tenshop.order.dto.OrderRegisterParam;
import tenshop.order.dto.OrderUpdateParam;

@ResponseAnnotation
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderBroker orderBroker;


    //todo registerParam 대한 validation 체크
    @PostMapping("/order")
    public Response register(@RequestBody OrderRegisterParam registerParam) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.register(registerParam));

        return Response.create(response);
    }

    @DeleteMapping("/order/{orderId}")
    public Response cancel(@PathVariable("orderId") Long orderId) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.cancel(orderId));

        return Response.create(response);
    }

    @PatchMapping("/order/{orderId}")
    public Response update(@PathVariable("orderId") Long orderId,
            @RequestBody OrderUpdateParam updateParam) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.update(orderId, updateParam));

        return Response.create(response);
    }

    @GetMapping("/order")
    public Response getOrders(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Response.create(orderBroker.getOrders(page, size));
    }

    @GetMapping("/order/{orderId}")
    public Response getOrderById(@PathVariable("orderId") Long orderId) {
        return Response.create(orderBroker.findById(orderId));
    }
}


