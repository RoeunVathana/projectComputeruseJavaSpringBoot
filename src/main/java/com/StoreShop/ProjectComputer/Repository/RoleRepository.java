package com.StoreShop.ProjectComputer.Repository;

import com.StoreShop.ProjectComputer.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    <T> ScopedValue<T> findByName(String name);
}
