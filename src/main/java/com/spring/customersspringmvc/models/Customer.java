package com.spring.customersspringmvc.models;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank(message = "Name cannot be empty and must contain at least one non-whitespace character")
    String name ;
    @NotBlank(message = "Address cannot be empty and must contain at least one non-whitespace character")
    String address ;
    @NotBlank(message = "Phone number cannot be empty and must contain at least one non-whitespace character")
    String phone ;
    @NotBlank(message = "Email cannot be empty and must contain at least one non-whitespace character")
    String email ;

}
