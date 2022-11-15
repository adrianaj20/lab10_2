package com.tecsup.petclinic.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OwnerDTO {
    private long id;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;

    public OwnerDTO(String name, String lastName, String address, String city, String telephone, Date birthDate) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.birthDate = birthDate;
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    @Override
    public String toString() {
        return "Owner [id=" + id +", name=" + name + ", lastName=" + lastName+", address=" + address+",city=" + city+",telephone="+telephone+", birthDate=" + birthDate+"]";
    }
}
