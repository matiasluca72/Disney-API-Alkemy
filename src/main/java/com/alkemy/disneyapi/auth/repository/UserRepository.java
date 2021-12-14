package com.alkemy.disneyapi.auth.repository;

import com.alkemy.disneyapi.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a UserEntity in the DB related to the username received
     *
     * @param username of the User to be found
     * @return The UserEntity if exists
     */
    UserEntity findByUsername(String username);
}
