package org.example.ddd;

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
