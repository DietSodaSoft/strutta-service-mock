package com.schultzco.webservices.services.daos;

import com.schultzco.webservices.models.SocialUser;
import com.schultzco.webservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    @Query("SELECT su FROM SocialUser su WHERE su.uuid = :uuid")
    public User findByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT su FROM SocialUser su WHERE su.typeId = :typeId")
    public User findByTypeId(@Param("typeId") String typeId);

}
