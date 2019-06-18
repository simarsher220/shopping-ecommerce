package org.codejudge.product.dao;

import org.codejudge.product.entity.Cart;
import org.codejudge.product.entity.CartProductKey;
import org.codejudge.product.entity.CartProduct;
import org.codejudge.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {


    CartProduct findByCartAndProduct(Cart cart, Product product);

    @Transactional
    @Modifying
    void deleteByCart(Cart cart);

    List<CartProduct> findAllByCart(Cart cart);

    @Transactional
    @Modifying
    void deleteByCartAndProduct(Cart cart, Product product);
}
