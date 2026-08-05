package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpResponse;
import com.StoreShop.ProjectComputer.Mapper.PermissionGroupMapper;
import com.StoreShop.ProjectComputer.Service.PermissionGroupService;
import com.StoreShop.ProjectComputer.Util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/permissionGroup")
@RestController
public class PermissionGpController {
    final PermissionGroupService permissionGroupService;

    public PermissionGpController(PermissionGroupService permissionGroupService, PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupService = permissionGroupService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionGpResponse>>> getPermissionGroups() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionGroupService.getList(),"Get permissionGroup Successfully"));
    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponse<PermissionGpResponse>> getPermissionGroupById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionGroupService.getById(Math.toIntExact(id))));
    }

}
