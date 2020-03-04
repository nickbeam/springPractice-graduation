package ru.topjava.graduation.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity{
    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

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
