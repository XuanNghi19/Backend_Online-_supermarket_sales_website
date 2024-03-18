package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.sql.Date;

import com.backend.Backend_supermarket.dtos.UserDTO;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    
    @Column(name = "active")
    private Boolean active;

    public static User fromUserDTO(UserDTO userDto){
        return User.builder()
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .phoneNumber(userDto.getPhoneNumber())
            .address(userDto.getAddress())
            .dateOfBirth(userDto.getDateOfBirth())
            .fullName(userDto.getFullName())
            .build();
    }
}
