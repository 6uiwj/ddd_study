package org.example.ddd;
//'받는 사람' 도메인 (받는 사람에 해당하는 필드들의 집합)
public class Receiver {
    private String name;
    private String phoneNumber;

    public Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 두 객체가 같은지 비교 (모든 속성 비교)
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if(other == null) return false;
        if(this == other) return true;
        if(!(other instanceof Receiver)) return false;
        Receiver that = (Receiver)other;
        return this.name.equals(that.name) && this.phoneNumber.equals(that.phoneNumber);
    }
}
