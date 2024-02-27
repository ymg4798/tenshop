package tenshop.core.product;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.product.converter.ProductStatusConverter;
import tenshop.core.product.converter.enums.ProductStatus;
import tenshop.core.product.domain.Category;
import tenshop.core.product.domain.ProductReview;

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

    private int stock;

    private int price;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;

    public Product(String name, Integer price, Integer stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static Product create(String name, Integer price, Integer stock, Category category) {
        return new Product(name, price, stock, category);
    }
}


