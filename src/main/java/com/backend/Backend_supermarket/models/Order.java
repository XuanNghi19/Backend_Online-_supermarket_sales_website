package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

import com.backend.Backend_supermarket.dtos.OrderDTO;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "status")
    private String status;

    @Column(name = "active")
    private Boolean active;

    public static Order fromOrderDTO(OrderDTO orderDTO, User user){
        return Order.builder()
            .user(user)
            .paymentMethod(orderDTO.getPaymentMethod())
            .paymentStatus(orderDTO.getPaymentStatus())
            .shippingAddress(orderDTO.getShippingAddress())
            .shippingDate(orderDTO.getShippingDate())
            .build();
    }
}
