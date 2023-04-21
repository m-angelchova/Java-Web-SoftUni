package bg.softuni.eventsdemo.web;

import bg.softuni.eventsdemo.model.OrderDto;
import bg.softuni.eventsdemo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/dummy/create")
    public void createDummyOrder(){
        OrderDto orderDto = new OrderDto();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            orderDto.addProductId(random.nextLong(100));
        }

        orderService.createOrder(orderDto);
    }
}
