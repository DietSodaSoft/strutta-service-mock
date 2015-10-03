package com.schultzco.webservices.services.daos;

import com.schultzco.webservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.uuid = :uuid")
    public User findByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT u FROM User u JOIN u.socialUsers su WHERE su.typeId = :typeId")
    public User findBySocialUserTypeId(@Param("typeId") String typeId);

}
