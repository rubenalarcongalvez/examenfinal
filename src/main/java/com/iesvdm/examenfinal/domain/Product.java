package com.iesvdm.examenfinal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 120)
    private String name;

    @Column(length = 255)
    private String descrip;

    @Column(name = "image_url", length = 120)
    private String imageUrl;

    @Column(length = 120)
    private String sku;

    @Column(precision = 10, scale = 3)
    private BigDecimal price;

    private BigInteger quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartItem> cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id"))
    private Category category;

    private long getCountCart() {
        return cart.size();
    }

}
