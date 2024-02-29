package tenshop.core.product;

import static tenshop.config.converter.EnumConverterUtils.*;

import java.util.ArrayList;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import tenshop.config.auditing.BaseTimeEntity;
import tenshop.core.product.converter.ProductStatusConverter;
import tenshop.core.product.converter.enums.ProductStatus;
import tenshop.core.product.domain.Category;
import tenshop.core.product.domain.ProductReview;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    private List<ProductReview> productReviews = new ArrayList<>();

    public Product(ProductStatus status, int stock, int price, String name, String content, Category category) {
        this.status = status;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.content = content;
        this.category = category;
    }

    public static Product create(String status, int stock, int price, String name, String content, Category category) {
        Product product = new Product(ofName(ProductStatus.class, status), stock, price, name, content, category);
        return product;
    }

    public void updateProduct(String status, String name, String content, Integer stock, Integer price) {
        if (status != null) this.status = ofName(ProductStatus.class, status);
        if (name != null) this.name = name;
        if (content != null) this.content = content;
        if (stock != null) this.stock = stock;
        if (price != null) this.price = price;
    }
}


