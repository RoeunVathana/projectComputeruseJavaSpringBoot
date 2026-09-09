package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpRequest;
import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpResponse;
import com.StoreShop.ProjectComputer.Mapper.PermissionGroupMapper;
import com.StoreShop.ProjectComputer.Response.SuccessResponse;
import com.StoreShop.ProjectComputer.Service.PermissionGroupService;
import com.StoreShop.ProjectComputer.Util.ApiResponse;
import com.StoreShop.ProjectComputer.Util.ApiResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.ok(
                                permissionGroupService.getList(),
                                "Get permission groups successfully"
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<PermissionGpResponse>> getPermissionGroupById(@PathVariable int id) {
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, permissionGroupService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionGpResponse>> createPermissionGroup(@Valid @RequestBody PermissionGpRequest permissionGpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(permissionGroupService.createPermissionGp(permissionGpRequest),"Create Permission Group Successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionGpResponse>> updatePermissionGroup(@Valid @RequestBody PermissionGpRequest permissionGpRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionGroupService.updatePermissionGp(permissionGpRequest, Math.toIntExact(id)), "Update Permission Group Successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionGpResponse>> deletePermissionGroup(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionGroupService.deletePermissionGp(Math.toIntExact(id)), "Delete Permission Group Successfully"));
    }
}
