package com.StoreShop.ProjectComputer.Repository;

import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionGroupRepository
        extends JpaRepository<PermissionGroupModel, Integer> {

}
