package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.Role.RoleRequest;
import com.StoreShop.ProjectComputer.DTO.Role.RoleResponse;
import com.StoreShop.ProjectComputer.Service.RoleService;
import com.StoreShop.ProjectComputer.Util.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/role")
//@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.roleService.getListRoles(), "Get Role Success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRoleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(roleService.getById(id), "Get Role Success"));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> filter(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.ok(
                                roleService.filterRole(name),
                                "Get Role Success"
                        )
                );
    }


    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> addRole(@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(this.roleService.createRole(roleRequest), "Create role success"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(@Valid @RequestBody RoleRequest roleRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.roleService.updateRole(roleRequest, id), "Update role success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> deleteRole(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.roleService.deleteRole(id), "Delete role success"));
    }
}
