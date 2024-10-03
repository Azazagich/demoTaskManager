package org.example.demotaskmanager.repository;

import org.example.demotaskmanager.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET role_id = null WHERE role_id = :id", nativeQuery = true)
    void updateToNullUsersByRoleId(@Param("id") Long id);
}
