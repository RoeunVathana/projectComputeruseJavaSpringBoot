package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.Repository.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private PermissionRepository permissionRepository;
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }



}
