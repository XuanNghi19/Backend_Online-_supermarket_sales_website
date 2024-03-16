package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email(message = "Email không hợp lệ!")
    @Column(name = "email", nullable = false, unique = false)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private Date createdAt ;
    @Column(name = "updated_at")
    private Date updated_at;
    @Column(name = "active")
    private Boolean active;

}
