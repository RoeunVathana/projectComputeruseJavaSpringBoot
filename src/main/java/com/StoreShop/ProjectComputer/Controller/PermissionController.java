package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.Permission.PermissionRequest;
import com.StoreShop.ProjectComputer.DTO.Permission.PermissionResponse;
import com.StoreShop.ProjectComputer.Service.PermissionGroupService;
import com.StoreShop.ProjectComputer.Service.PermissionService;
import com.StoreShop.ProjectComputer.Util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    private final PermissionService permissionService;
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getList (){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionService.getList(), "Permission Not Found!"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponse>> createPermission(@RequestBody PermissionRequest permissionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(permissionService.create(permissionRequest), "Permission Created!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponse>> updatePermission(@PathVariable Long id, @RequestBody PermissionRequest permissionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionService.update(permissionRequest,id), "Permission Updated!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponse>> deletePermission(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(permissionService.delete(id), "Permission Deleted!"));
    }


}
