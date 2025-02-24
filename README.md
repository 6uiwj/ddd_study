<h1>도메인 모델 </h1>

![ddd drawio (1)](https://github.com/user-attachments/assets/20a44aa8-a1e6-472a-af8a-7354c2936d20)



<h1> 요구사항 </h1>
<li> 최소 한 종류 이상의 상품을 주문해야 한다. </li>
<li>한 상품을 한 개 이상 주문할 수 있다.</li>
<li>총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.</li>
<li>각 상품의 구매 가격 합은 상품 라격에 구매 개수를 곱한 값이다.</li>
<li>주문할 때 배송지 정보를 반드시 지정해야 한다.</li>
<li>배송지 정보는 받는 사람 이름, 전화번호, 주소로 구성된다.</li>
<li>출고를 하면 배송지를 변경할 수 없다.</li>
<li>출고 전에 주문을 취소할 수 있다.</li>
<li>고객이 결제를 완료하기 전에는 상품을 준비하지 않는다. </li>
<br/>
<br/>
<h1>엔티티</h1>
<h3>엔티티</h3>

- 식별자를 가짐(=기본키)
- 엔티티는 객체마다 고유해서 각 엔티티는 서로 다른 식별자를 갖는다

<br/>
<br/>
<h3>식별자 생성규칙</h3>

- 특정 규칙에 따라 생성
- UUID나 Nano ID와 같은 고유 식별자 생성기 사용
- 값을 직접 입력
- 일련번호 사용 (시퀀스나 DB의 자동 증가 칼럼 사용)

<hr>

1. UUID (Universally unique identifier) : 개발 언어에서 제공하는 식별자 생성기

2. 일련번호 : DB에서 자동 증가 기능 사용(ex) oracle의 sequence)
   → DB에 데이터를 삽입해야 비로소 값을 알 수 있기 때문에 데이터를 추가하기 전에는 식별자를 알 수 없다.
   → 엔티티 객체를 생성할 때 식별자를 전달할 수 없음

```
Article article = new Article(author, title, ...);
articleRepository.save(article); // DB에 저장한 뒤 구한 식별자는 엔티티에 반영 
Long savedArticleId = article.getId(); ///DB에 저장한 후 식별자 참조 가능
  
```

  3. 기타 방법: 식별자는 먼저 만들고 엔티티 객체를 생성할 때 식별자 전달

  ```
//엔티티를 생성하기 전에 식별자 생성
String orderNumber = orderRepository.generateOrderNumber();

Order order = new Order(orderNumber, ...);
orderRepository.save(order);
```

<br/>
<br/>
<h1>밸류</h1>
- 밸류: 하나의 개념을 타나태는 필드들의 집합 <br/>
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/148047079/414156435-b51e1506-c657-4436-a05e-f6da69f9240b.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20250218%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250218T082744Z&X-Amz-Expires=300&X-Amz-Signature=d0f82cf73f121babc1a7841199ae14fab93802b4dd2d7a0fe929e6dcd520fef8&X-Amz-SignedHeaders=host">

- 꼭 두개 이상의 데이터를 가져야 하는 것은 아님, 의미를 명확하게 하기 위해 밸류 타입을 사용하는 경우도 있음
OrderLine에서 price와 amounts는 int타입이지만 정확히는 '돈'을 나타내므로, '돈'을 의미하는 Money 타입으로 밸류를 만들 수 있음

```
package org.example.ddd;
//OrerLine의 amounts와 price가 의미하는 '돈'=Money타입을 정의하는 밸류
//price와 amounts가 금액을 의미하는 것을 나타내기 위해

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

```

- 밸류 객체의 데이터를 변경할 때에는 기존 데이터를 변경하기보다 새 객체를 생성  (불변 객체(코드의 안정성을 위해))

<br/>
<br/>

<h1>네 개의 영역</h1>
<li> 표현 영역 </li>
<li> 응옹 영역 </li>
<li> 도메인 영역</li>
<li> 인프라스트럭처 영역</li>

<br/>
<h2>표현 영역</h2>
<li> 사용자의 요청을 받아 해석해서 응용 영역에 전달 (ex: 웹 브라우저가 HTTP 요청 파라미터로 전송한 데이터를 응용 서비스가 요구하는 형식의 객체 타입으로 변환해서 전달) </li>
<li> 응용 영역의 처리 결과를 다시 사용자에게 보여주는 역할 (ex: 응용 서비스가 리턴한 결과를 JSON 형식으로 변환해서 HTTP 응답으로 웹 브라우저에 전송)  </li>
<li> 스프링 MVC 프레임워크가 표현 영역을 위한 기술임 </li>

![ddd-4개의영역 drawio](https://github.com/user-attachments/assets/5ff16f72-5896-4046-afd1-87b59fdbb531)

<h2>응용 영역</h2>
<li>시스템이 사용자에게 제공해야 할 기능 구현 (ex: 주문 등록, 주문 취소, 상품 상세 조회)</li>
<li>기능 구현을 위해 도메인 영역의 도메인 모델을 사용, 실제 도메인 로직 구현은 도메인 모델에 위임</li>

![ddd-응용영역 drawio](https://github.com/user-attachments/assets/86734b93-91ca-4f90-95bd-ad47eb029f62)

<br/>
<h2>도메인 영역</h2>
<li>도메인 모델 구현</li>
<li>도메인의 핵심 로직 구현 (ex: 주문 도메인 - 배송지 변경, 결제완료, 주문 총액 계산)</li>


<br/>
<h2>인프라스트럭처 영역</h2>
<li>구현 기술에 대한 것</li>
<li>RDBMS 연동을 처리, 메시징 큐에 메시지를 전송하거나 수신하는 기능 구현</li>
<li>SMTP를 이용한 메일 발송 기능을 구현하거나 HTTP 클라이언트를 이용해서 REST API를 호출하는 것도 처리</li>
<li>논리적인 개념을 표현하기보다 실제 구현을 다룸</li>
<li>도메인 영역, 응용 영역, 표현 영역은 구현 기술을 사용한 코드를 직접 만들지 않고, 인프라스트럭처 영역에서 제공하는 기능을 사용해서 필요한 기능을 개발 </li>
