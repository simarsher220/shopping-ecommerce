package com.ecommerce.rest.dao;

import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.entity.UserProduct;
import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.entity.UserProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, UserProductKey> {
    UserProduct findByUserAndProduct(User user, Product product);

    List<UserProduct> findByUser(User user);
}
