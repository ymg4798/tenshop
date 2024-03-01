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

    public Product(ProductStatus status, int stock, int price, String name, String content) {
        this.status = status;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.content = content;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }

    public static Product create(String status, int stock, int price, String name, String content, Category category) {
        int finalStock = isStatusSoldOut(status) ? 0 : stock;
        Product product = new Product(ofName(ProductStatus.class, status), finalStock, price, name, content);
        product.setCategory(category);
        return product;
    }

    public void updateProduct(String content, int stock) {
        this.content = content;
        this.stock = stock;
    }

    public void statusUpdate(String status) {
        this.status = ofName(ProductStatus.class, status);
    }

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }

    public static boolean isStatusSoldOut(String status){
        return "품절".equals(status);
    }
}


