package bg.softuni.eventsdemo.service;

import bg.softuni.eventsdemo.model.OrderCreatedEvent;
import bg.softuni.eventsdemo.model.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderService.class);

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    public void createOrder(OrderDto orderDto){

        LOGGER.info("Order was created");
        OrderCreatedEvent createdEvent = new OrderCreatedEvent(this)
                .setProductIds(orderDto.getProductIds());

        publisher.publishEvent(createdEvent);
    }
}
