package com.RestaurantManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price (rubles)", nullable = false)
    private Long price;

    @Column(name = "cooking_time", nullable = false)
    private Duration cookingTime;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "weight (grams)", nullable = false)
    private Integer weight;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dish")
    private List<ImageModel> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<ReviewModel> reviews;
}
