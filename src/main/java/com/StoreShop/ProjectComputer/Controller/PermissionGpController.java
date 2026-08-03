package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.Service.PermissionGroupService;
import org.springframework.stereotype.Controller;

@Controller
public class PermissionGpController {
    final PermissionGroupService permissionGroupService;
    public PermissionGpController(PermissionGroupService permissionGroupService) {
        this.permissionGroupService = permissionGroupService;
    }

}
