package com.StoreShop.ProjectComputer.Repository;

import com.StoreShop.ProjectComputer.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository
        extends JpaRepository<RoleModel, Integer>,
        JpaSpecificationExecutor<RoleModel> {
}