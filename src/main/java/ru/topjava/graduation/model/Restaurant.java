package ru.topjava.graduation.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity{

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 250)
    private String address;

    @Column(name = "phone", nullable = false)
    @NotBlank
    @Size(max = 50)
    private String phone;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    private String email;

    public  Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, String phone, String email) {
        super(id, name);
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
