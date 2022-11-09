package cl.pinolabs.backend.controller.security.repository;

import cl.pinolabs.backend.modelo.entity.ERole;
import cl.pinolabs.backend.modelo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}