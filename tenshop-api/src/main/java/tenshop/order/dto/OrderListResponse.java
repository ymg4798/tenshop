package tenshop.order.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderListResponse {


    private int paymentPrice;
    private LocalDateTime createDate;

}
