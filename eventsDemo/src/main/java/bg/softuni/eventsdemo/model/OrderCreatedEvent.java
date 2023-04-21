package bg.softuni.eventsdemo.model;

import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class OrderCreatedEvent extends ApplicationEvent {
    private List<Long> productIds = new ArrayList<>();

    public OrderCreatedEvent(Object source) {
        super(source);
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public OrderCreatedEvent setProductIds(List<Long> productIds) {
        this.productIds = productIds;
        return this;
    }


    public OrderCreatedEvent addProductId(Long productId) {
        this.productIds.add(productId);
        return this;
    }
}
