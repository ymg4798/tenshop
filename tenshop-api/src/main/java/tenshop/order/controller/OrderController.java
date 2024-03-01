package tenshop.order.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

    /*
    todo 추가적으로 해야할것
    1. 들어오는 값들 validation 체크 완료
    2. 포인트 감소, 증가
    3. 재고 감소, 증가
    4. 변수명, 메서드명 갈무리 그리고 리턴 값 갈무리
    5.

     */

    @PostMapping("/order")
    public Response register(@RequestBody @Valid OrderRegisterParam registerParam) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.register(registerParam));

        return Response.create(response);
    }

    @DeleteMapping("/order/{orderId}")
    public Response cancel(@PathVariable("orderId") @Valid @Positive Long orderId) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.cancel(orderId));

        return Response.create(response);
    }

    @PatchMapping("/order/{orderId}")
    public Response update(@PathVariable("orderId") @Valid @Positive Long orderId,
            @RequestBody @Valid OrderUpdateParam updateParam) {
        Map<String, String> response = new HashMap<>();

        response.put("message", orderBroker.update(orderId, updateParam));

        return Response.create(response);
    }

    @GetMapping("/order")
    public Response getOrders(
            @RequestParam(name = "page", defaultValue = "0") @Valid @Positive int page,
            @RequestParam(name = "size", defaultValue = "10") @Valid @Positive int size) {
        Long userId = 1L;
        return Response.create(orderBroker.findOrders(userId, page, size));
    }

    @GetMapping("/order/{orderId}")
    public Response getOrderById(@PathVariable("orderId") @Valid @Positive Long orderId) {
        return Response.create(orderBroker.findOrderInformation(orderId));
    }
}


