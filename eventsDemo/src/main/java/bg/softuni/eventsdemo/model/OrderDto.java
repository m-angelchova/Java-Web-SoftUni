package bg.softuni.eventsdemo.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private List<Long> productIds = new ArrayList<>();


    public List<Long> getProductIds() {
        return productIds;
    }

    public OrderDto setProductIds(List<Long> productIds) {
        this.productIds = productIds;
        return this;
    }


    public OrderDto addProductId(Long productId) {
        this.productIds.add(productId);
        return this;
    }
}
