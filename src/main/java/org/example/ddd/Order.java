package org.example.ddd;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.UUID;


public class Order {

    //UUID uuid = UUID.randomUUID(); //식별자 생성하기
    //String strUuid = uuid.toString();

    private OrderNo id;

    public OrderNo getId() {
        return id;
    }
    private OrderState state; //주문상태
    private ShippingInfo shippingInfo; //배송정보
    private List<OrderLine> orderLines; //주문항목 리스트 가져오기
    private Money totalAmounts;

    private String orderNumber;

    /**
     * 주문항목 클래스에서 주문항복을 가져와 List에 주입
     * @param orderLines
     * @param shippingInfo : 주문할 때 배송지 정보 반드시 지정
     * 도메인 규칙 : 배송 정보는 필수
     */
    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo,
                 OrderState state) {
        setOrderer(orderer);
        setOrderLines(orderLines);
    }

    public void setOrderer(Orderer orderer) {
        if(orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }


    /**
     * 생성자
     * @param shippingInfo
     */
    public void setShippingInfo(ShippingInfo shippingInfo) {
        if(shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    /**
     * 주문항목 데이터 주입, 주문항목에서 구매가격을 구함, 요구사항 검증
     * @param orderLines
     */
    private void setOrderLines(List<OrderLine> orderLines) {
        verityAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts(); //총 주문 금액 계산
    }

    /**
     * 최소 1개 이상 제품을 구매하는지 검증
     * @param orderLines
     */
    private void verityAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if(orderLines == null || orderLines.isEmpty()) { //주문 정보가 없으면 예외 던짐
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    /**
     * 총 구매 가격
     */
    private void calculateTotalAmounts() {
        //코드 확인 필요
        this.totalAmounts = new Money(orderLines.stream().mapToInt(x -> x.getAmounts()).sum());
    }

    /**
     * 배송정보 변경
     * 주문이 출고 이전 상태면 배송정보 변경 진행, 아니면 예외발생(verifyNotYetShipped()에서)
     * @param newShippingInfo 새로운 배송정보
     */
    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
    }

    /**
     * 주문 취소
     * 주문이 취소되면, 주문 상태를 취소로 바꾼다.
     */
    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    /**
     * 주문상태가 결제 대기가 아니고, 준비중이 아니라면 아직 출고 전인 상태이다.
     */
    private void verifyNotYetShipped() {
        if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalStateException("aleady shipped");
    }

    /**
     * 배송정보 변경 가능여부
     * @return
     */
    private boolean isShippingChangeable() {
        if (state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING) {
            return true;
        } else if (state == OrderState.SHIPPED || state == OrderState.DELIVERING ||
                state == OrderState.DELIVERY_COMPLETED) {
            return false;
        }
        return false;
    }

    public void ChangeShipped() {}
    public void completePayment(){}

    /**
     * 엔티티를 구현한 클래스에서 식별자를 이용하여 equals와 hashCode() 메서드 구현하기
     * @param obj
     * @return
     */
//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj) return true;
//        if(obj == null) return false;
//        if(obj.getClass() != Order.class) return false;
//        return this.orderNumber.equals(other.orerNumber);
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
//        return result;
//    }

}

