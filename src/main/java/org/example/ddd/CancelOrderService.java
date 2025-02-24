package org.example.ddd;

/**
 * 응용 영역
 * 응용 서비스는 로직을 직접 수행하기보다는 도메인 모델에 로직 수행을 위임
 */
public class CancelOrderService {

    @Transactional
    public void cancelOrder(String orderId) {
        Order order = findOrderById(orderId);
        if(order == null) throw new OrderNotFoundException(orderId);
        order.cancel();
    }
}
