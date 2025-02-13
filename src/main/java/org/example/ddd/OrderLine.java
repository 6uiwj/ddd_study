package org.example.ddd;

/**
 * 주문 항목
 */
public class OrderLine {
    private Product product; //제품
    private int price; //가격
    private int quantity; //수량
    private int amounts; //총 주문 가격

    //생성자
    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    //총 가격 계산
    private int calculateAmounts() {
        return price*quantity;
    }

    public int getAmounts(){
        return this.amounts;
    }
}
