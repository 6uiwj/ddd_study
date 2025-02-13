package org.example.ddd;

import java.util.List;

public class Order {
    private OrderState state; //주문상태
    private ShippingInfo shippingInfo; //배송정보
    private List<OrderLine> orderLines; //주문항목 리스트 가져오기
    private Money totalAmounts;

    /**
     * 주문항목 클래스에서 주문항복을 가져와 List에 주입
     * @param orderLines
     * @param shippingInfo : 주문할 때 배송지 정보 반드시 지정
     */
    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    /**
     * 도메인 규칙 : 배송 정보는 필수
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
        int sum = orderLines.stream() //주문 항목
                .mapToInt(x -> x.getAmounts()) //주문 항복에서 총 구매가격을 가져옴
                .sum(); //전부 합함
        this.totalAmounts = new Money(sum);
    }

    /**
     * 배송정보 변경
     *
     * @param newShippingInfo 새로운 배송정보
     */
    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!isShippingChangeable()) { //배송정보 변경가능?
            throw new IllegalStateException("can't change shipping in " + state);
        }
        this.shippingInfo = newShippingInfo;
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
    public void cancel() {}
    public void completePayment(){}

}

