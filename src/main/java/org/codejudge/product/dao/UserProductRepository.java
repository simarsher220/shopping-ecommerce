package org.codejudge.product.dao;

import org.codejudge.product.entity.Product;
import org.codejudge.product.entity.User;
import org.codejudge.product.entity.UserProduct;
import org.codejudge.product.entity.UserProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, UserProductKey> {
    UserProduct findByUserAndProduct(User user, Product product);

    List<UserProduct> findByUser(User user);
}
