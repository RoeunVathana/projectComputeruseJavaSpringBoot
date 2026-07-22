package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.Role.RoleRequest;
import com.StoreShop.ProjectComputer.DTO.Role.RoleResponse;
import com.StoreShop.ProjectComputer.Service.RoleService;
import com.StoreShop.ProjectComputer.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> addRole(@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(this.roleService.createRole(roleRequest), "Create role success"));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(@Valid @RequestBody RoleRequest roleRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.roleService.updateRole(roleRequest, id), "Update role success"));
    }

}
