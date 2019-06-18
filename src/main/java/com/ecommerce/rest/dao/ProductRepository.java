package com.ecommerce.rest.dao;

import com.ecommerce.rest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory_categoryId(Integer categoryId);

    Product findByProductId(Integer productId);

    @Query(nativeQuery = true, value = "update product set rating = :rating")
    void rateProduct(@Param("rating") Double rating);
}
