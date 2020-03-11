package com.gmail.gayko.andrey.contacts;

public class Contact {

    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private String birthDate;

    public Contact(){
    }

    public Contact(int id, String name, String phoneNumber, String address, String birthDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

    public Contact(String name, String phoneNumber, String address, String birthDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
