package tenshop.core.product.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import tenshop.core.product.Product;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(columnDefinition = "varchar(10)")
    private String name;

    private int depth;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Category(String name, int depth) {
        this.name = name;
        this.depth = depth;
    }

    public void setParent(Category parent) {
        this.parent = parent;
        parent.getChildren().add(this);

    }

    public void addChildren(Category child) {
        this.children.add(child);
        child.setParent(this);
    }

    public static Category create(String name, int depth, Category parent, Category... children) {
        Category category = new Category(name, depth);
        category.validateDepth();
        if (parent != null) {
            category.setParent(parent);
        }
        for (Category child : children) {
            category.addChildren(child);
        }
        return category;
    }

    public void validateDepth() {
        if (this.depth >= 4) {
            throw new IllegalArgumentException("카테고리 분류는 소분류까지만 가능합니다.");
        }
    }

    public void update(String name) {
        this.name = name;
    }

    public void validateDeletion() {
        if (!this.children.isEmpty()) {
            throw new IllegalStateException("자식 카테고리가 존재하기 때문에 삭제할 수 없습니다.");
        }
    }
}


