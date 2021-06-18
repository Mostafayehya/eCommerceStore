package com.store.repository;

import com.store.model.Product;
import com.store.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Page<Product> findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(Collection<Subcategory> subcategory, Double price, Double price2, String name, Pageable pageable);

    List<Product> findByUser_Id(int sellerId, Pageable pageable);
}
