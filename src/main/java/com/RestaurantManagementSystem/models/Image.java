package com.RestaurantManagementSystem.models;

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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "img_binary", columnDefinition = "longblob")
    private byte[] imgBinary;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Dish dish;
}
