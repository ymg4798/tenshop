package tenshop.core.product.domain;

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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(columnDefinition = "varchar(10)")
    private String name;

    private int categoryDepth;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> children;

    public Category(String name, Category parent, int categoryDepth) {
        this.name = name;
        this.parent = parent;
        this.categoryDepth = categoryDepth;
    }

    public void update(String name, Category parent, int categoryDepth) {
        this.name = name;
        this.parent = parent;
        this.categoryDepth = categoryDepth;
    }

    public static Category create(String name, Category parent, int categoryDepth) {
        return new Category(name, parent, categoryDepth);
    }
}


