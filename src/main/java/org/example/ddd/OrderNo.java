package org.example.ddd;

//value타입 '주문번호'임을 확실히 나타내 주기위해
public class OrderNo {
    private int value;

    public OrderNo(int value) {
        this.value=value;
    }

    public int getValue() {
        return this.value;
    }
}
