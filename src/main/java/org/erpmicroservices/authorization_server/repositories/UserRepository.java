package org.erpmicroservices.authorization_server.repositories;

import org.erpmicroservices.authorization_server.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByUsername(String username);
}
