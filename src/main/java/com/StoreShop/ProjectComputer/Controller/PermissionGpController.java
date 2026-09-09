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
    public ResponseEntity<SuccessResponse<List<PermissionGpResponse>>> getPermissionGroups() {

        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, permissionGroupService.getList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<PermissionGpResponse>> getPermissionGroupById(@PathVariable int id) {
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, permissionGroupService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<PermissionGpResponse>> createPermissionGroup(@Valid @RequestBody PermissionGpRequest permissionGpRequest) {
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.CREATED, permissionGroupService.createPermissionGp(permissionGpRequest))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<PermissionGpResponse>> updatePermissionGroup(@Valid @RequestBody PermissionGpRequest permissionGpRequest, @PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, permissionGroupService.updatePermissionGp(permissionGpRequest, Math.toIntExact(id)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<PermissionGpResponse>> deletePermissionGroup(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, permissionGroupService.deletePermissionGp(Math.toIntExact(id)))
        );
    }
}
