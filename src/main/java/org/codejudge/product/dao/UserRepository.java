package org.codejudge.product.dao;

import org.codejudge.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserId(UUID userId);

    User findByUsernameAndPassword(String username, String password);
}
