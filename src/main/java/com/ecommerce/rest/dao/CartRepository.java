package com.ecommerce.rest.dao;

import com.ecommerce.rest.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    Cart findByCartId(UUID cartId);
}
