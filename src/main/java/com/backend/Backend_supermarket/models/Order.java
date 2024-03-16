package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "total_money")
    private Float totalMoney;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "shipping_date")
    private Date shippingDate;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "status")
    private String status;
    @Column(name = "active")
    private Boolean active;
}
