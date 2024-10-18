package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "brand")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE brand SET deleted = true WHERE id = ?")
@SQLRestriction( "deleted = false")
public class Brand extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<BrandCategory> categories = new HashSet<>();

    @Column
    @ColumnDefault(value = "0")
    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false

    public Brand(String name, List<Category> categories) {
        this.name = name;
        this.categories = categories.stream()
                .map(it -> new BrandCategory(this,it))
                .collect(Collectors.toSet());
        this.updateCreatedDateAndCreatedBy();
    }

    public void updateBrandName(String brandName) {
        this.name = brandName;
    }

}

