package org.example.ddd;
//OrerLine의 amounts와 price가 의미하는 '돈'=Money타입을 정의하는 밸류
//price와 amounts가 금액을 의미하는 것을 나타내기 위해
//불변 객체
public class Money {
    private int value;

    public Money(int value) {
        this.value=value;
    }

    public int getValue() {
        return this.value;
    }

    //돈 계산 기능 추가
    //밸류 객체의 데이터를 변경할 때에는 기존 데이터를 변경하기보다 새로운 객체 생성
    public Money add(Money money) {
        return new Money(this.value + money.value); //기존 금액 + 매개변수로 추가된 금액
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }


}
