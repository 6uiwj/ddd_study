package org.example.ddd;

/**
 * 주문 상태
 * PAYMENT_WAITING : 결제대기 -> 배송정보 변경 가능
 * PREPARING : 준비중 -> 배송정보 변경 가능
 * SHIPPED : 출고완료 -> 배송정보 변경 불가
 * DELIVERING : 배송중 -> 배송정보 변경 불가
 * DELIVERY_COMPLETED : 배송완료 -> 배송정보 변경 불가
 */
public enum OrderState {
    PAYMENT_WAITING,
    PREPARING,
    SHIPPED, DELIVERING, DELIVERY_COMPLETED;

}
