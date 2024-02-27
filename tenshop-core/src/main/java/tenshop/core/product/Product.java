package tenshop.core.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.product.converter.ProductStatusConverter;
import tenshop.core.product.converter.enums.ProductStatus;
import tenshop.core.product.domain.Category;
import tenshop.core.product.domain.ProductReview;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Convert(converter = ProductStatusConverter.class)
    @Column(columnDefinition = "varchar(10)")
    private ProductStatus status;

    @Column(columnDefinition = "int(4)")
    private int stock;

    @Column(columnDefinition = "int(11)")
    private int price;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;
}


