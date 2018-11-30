package org.fdse.userservice.repository;

import org.fdse.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserId(Integer userId);
    User findUserByUserName(String userName);
}
