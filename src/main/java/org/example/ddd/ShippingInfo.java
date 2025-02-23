package org.example.ddd;

/**
 * 도메인 영역
 */
public class ShippingInfo {
   private Receiver receiver;
   private Address address;

    public Receiver getReceiver() {
        return receiver;
    }

    public Address getAddress() {
        return address;
    }

    public ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }
}
