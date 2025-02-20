package org.example.ddd;

/**
 * 주문 항목
 */
public class OrderLine {
    private Product product; //제품
    private Money price; //가격
    private int quantity; //수량
    private int amounts; //총 주문 가격

    //생성자
    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = new Money(price.getValue()); //값 변경이 아닌 새 객체 생성
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    //총 가격 계산
    private int calculateAmounts() {
        return price.getValue()*quantity;
    }

    public int getAmounts(){
        return this.amounts;
    }
}
