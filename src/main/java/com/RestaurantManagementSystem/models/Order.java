package com.RestaurantManagementSystem.models;

import com.RestaurantManagementSystem.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {  // TODO: set up nullable for fields.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<Dish> cookingDishes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<Dish> cookedDishes = new ArrayList<>();

    @Column(name = "cost (rubles)")
    private Long cost;

    @Column(name = "start_time")
    private Instant startTime = Instant.now();

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "status")
    private Status status = Status.COOKING;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    // TODO: nullable = false.
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void init() {
        cost = cookingDishes.stream().mapToLong(Dish::getPrice).sum();  // TODO: implement summa cookingDishes.
    }
}
