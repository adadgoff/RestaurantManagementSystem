package com.RestaurantManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Lob
    @Column(name = "img_binary", nullable = false)
    private byte[] imgBinary;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private DishModel dish;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "profileIcon")
    private UserModel user;
}
