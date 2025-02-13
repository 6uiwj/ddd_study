package org.example.ddd;

public class ShippingInfo {
    private String receiverName; //수취인
    private String shippingAddress1; //배송주소 1
    private String shippingAddress2; //배송주소2
    private String shippingZipcode; //우편번호

    public String getReceiverName() {
        return receiverName;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public String getShippingZipcode() {
        return shippingZipcode;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public void setShippingZipcode(String shippingZipcode) {
        this.shippingZipcode = shippingZipcode;
    }
}
