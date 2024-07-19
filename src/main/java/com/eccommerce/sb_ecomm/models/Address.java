package com.eccommerce.sb_ecomm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min=5, message="Street name must be atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min=4, message="Street name must be atleast 4 characters")
    private String buildingName;

    @NotBlank
    @Size(min=4, message="Street name must be atleast 4 characters")
    private String city;
    @NotBlank
    @Size(min=4, message="Street name must be atleast 4 characters")
    private String country;

    @NotBlank
    @Size(min = 6,message = "pincode must be atleast 6 character")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users=new ArrayList<>();

    public Address(String street, String buildingName, String city, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }
}
