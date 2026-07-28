package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.DTO.Role.RoleRequest;
import com.StoreShop.ProjectComputer.DTO.Role.RoleResponse;
import com.StoreShop.ProjectComputer.Mapper.RoleMapper;
import com.StoreShop.ProjectComputer.Model.PermissionModel;
import com.StoreShop.ProjectComputer.Model.RoleModel;
import com.StoreShop.ProjectComputer.Repository.PermissionRepository;
import com.StoreShop.ProjectComputer.Repository.RoleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    // Get all roles
    public List<RoleResponse> getListRoles() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return roleRepository.findAll(sort)
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    //Get by id
    public RoleResponse getById(Integer id) {
        RoleModel roleModel = roleRepository.findById(id).orElseThrow(()-> new RuntimeException("Role Not Found!"));
        return roleMapper.toResponse(roleModel);
    }

    public List<RoleResponse> filterRole(String name) {

        Specification<RoleModel> spec = Specification.unrestricted();

        if (name != null && !name.isBlank() && !name.equalsIgnoreCase("null")) {

            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"
                    )
            );
        } else {
            throw new RuntimeException("Name is empty!");
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<RoleModel> roles = this.roleRepository.findAll(spec, sort);

        return roles.stream()
                .map(roleMapper::toResponse)
                .toList();
    }


    // Create role
    public RoleResponse createRole(RoleRequest req) {

        // Create new Role
        RoleModel roleModel = new RoleModel();

        // Find permission by ID
        PermissionModel permissionModel = permissionRepository
                .findById(Math.toIntExact(req.getPermission_id()))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Permission not found with ID: "
                                        + req.getPermission_id()
                        )
                );

        // Set Role information
        roleModel.setName(req.getName());
        roleModel.setDescription(req.getDescription());
        roleModel.setStatus(req.getStatus());

        // Create Set of Permissions
        Set<PermissionModel> permissions = new HashSet<>();

        // Add permission
        permissions.add(permissionModel);

        // Set permissions to role
        roleModel.setPermissions(permissions);

        // Save Role
        RoleModel savedRole = roleRepository.save(roleModel);

        // Convert Entity -> Response DTO
        return roleMapper.toResponse(savedRole);
    }

    //update Role
    public RoleResponse updateRole(RoleRequest req, Long id) {

        // Find existing role
        RoleModel roleModel = roleRepository.findById(Math.toIntExact(id))
                .orElseThrow(() ->
                        new RuntimeException("Role Not Found with ID: " + id)
                );

        // Find permission by ID
        PermissionModel permissionModel = permissionRepository
                .findById(Math.toIntExact(req.getPermission_id()))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Permission Not Found with ID: "
                                        + req.getPermission_id()
                        )
                );

        // Update role information
        roleModel.setName(req.getName());
        roleModel.setDescription(req.getDescription());
        roleModel.setStatus(req.getStatus());

        // Create Permission Set
        Set<PermissionModel> permissions = new HashSet<>();

        // Add permission
        permissions.add(permissionModel);

        // Set permission to role
        roleModel.setPermissions(permissions);

        // Save updated role
        RoleModel savedRole = roleRepository.save(roleModel);

        // Convert Entity -> Response DTO
        return roleMapper.toResponse(savedRole);
    }

    //delete role
    public RoleResponse deleteRole(Long id) {
        RoleModel roleModel = this.roleRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new RuntimeException("Role Not Found with ID: " + id)
        );

        RoleResponse response = roleMapper.toResponse(roleModel);
        roleRepository.delete(roleModel);
        return response;
    }

}