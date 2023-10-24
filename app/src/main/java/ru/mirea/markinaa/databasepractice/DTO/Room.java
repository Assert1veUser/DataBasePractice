package ru.mirea.markinaa.databasepractice.DTO;

public class Room {
    private String id;
    private String address;
    private String postcode;
    private String phoneNumber;
    private String numberOfEmployee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(String numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }
}
