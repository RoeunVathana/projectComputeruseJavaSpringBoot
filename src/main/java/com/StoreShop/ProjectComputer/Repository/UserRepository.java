package com.StoreShop.ProjectComputer.Repository;

import com.StoreShop.ProjectComputer.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmailIgnoreCase(String email);

    Optional<UserModel> findByPhone(String phone);

    Optional<UserModel> findByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhone(String phone);
}
