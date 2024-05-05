package com.backend.Backend_supermarket.data;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

////        String email = "@example.com";
////        String password = "password";
////        String address = " Main St";
////        String avatar = "assets/user_image_default.jpg";
////        String role;
////
////        List<User> clientList = new ArrayList<User>();
////        role = "client";
////        for(int i = 0; i < 5; i++){
////            UserDTO newUserDTO = UserDTO.builder()
////                    .email("client" + i +email)
////                    .password("password" + i)
////                    .phoneNumber("0123456789")
////                    .address(i + address)
////                    .dateOfBirth(Date.valueOf("1990-01-01"))
////                    .fullName("Client " + i)
////                    .avatar(avatar)
////                    .role(role)
////                    .build();
////            User newUser = User.fromUserDTO(newUserDTO);
////            newUser.setActive(true);
////            clientList.add(newUser);
////        }
////        userRepository.saveAll(clientList);
////
////        UserDTO adminDTO = UserDTO.builder()
////                .email("admin" +email)
////                .password("password")
////                .phoneNumber("0123456789")
////                .address(address)
////                .dateOfBirth(Date.valueOf("1990-01-01"))
////                .fullName("Admin")
////                .avatar(avatar)
////                .role("admin")
////                .build();
////        User admin = User.fromUserDTO(adminDTO);
////        admin.setActive(true);
////        userRepository.save(admin);
////
////        List<User> inventoryList = new ArrayList<User>();
////        role = "inventory";
////        for(int i = 0; i < 5; i++){
////            UserDTO newUserDTO = UserDTO.builder()
////                    .email("inventory" + i +email)
////                    .password("password" + i)
////                    .phoneNumber("0123456789")
////                    .address(i + address)
////                    .dateOfBirth(Date.valueOf("1990-01-01"))
////                    .fullName("Inventory " + i)
////                    .avatar(avatar)
////                    .role(role)
////                    .build();
////            User newUser = User.fromUserDTO(newUserDTO);
////            newUser.setActive(true);
////            inventoryList.add(newUser);
////        }
////        userRepository.saveAll(inventoryList);
////
////        List<User> salesList = new ArrayList<User>();
////        role = "sales";
////        for(int i = 0; i < 5; i++){
////            UserDTO newUserDTO = UserDTO.builder()
////                    .email("sales" + i +email)
////                    .password("password" + i)
////                    .phoneNumber("0123456789")
////                    .address(i + address)
////                    .dateOfBirth(Date.valueOf("1990-01-01"))
////                    .fullName("Sales " + i)
////                    .avatar(avatar)
////                    .role(role)
////                    .build();
////            User newUser = User.fromUserDTO(newUserDTO);
////            newUser.setActive(true);
////            salesList.add(newUser);
//        }
//        userRepository.saveAll(salesList);
    }
}
