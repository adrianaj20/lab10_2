package com.tecsup.petclinic.dto;

public class OwnerDTO {
    private long id;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
   
    public OwnerDTO(String name, String lastName, String address, String city, String telephone) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Override
    public String toString() {
        return "Owner [id=" + id +", name=" + name + ", lastName=" + lastName+", address=" + address+",city=" + city+",telephone="+telephone+"]";
    }
}