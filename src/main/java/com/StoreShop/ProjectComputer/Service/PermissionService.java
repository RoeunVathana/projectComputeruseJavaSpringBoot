package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.DTO.Permission.PermissionRequest;
import com.StoreShop.ProjectComputer.DTO.Permission.PermissionResponse;
import com.StoreShop.ProjectComputer.Mapper.PermissionMapper;
import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import com.StoreShop.ProjectComputer.Model.PermissionModel;
import com.StoreShop.ProjectComputer.Repository.PermissionGroupRepository;
import com.StoreShop.ProjectComputer.Repository.PermissionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final PermissionGroupRepository permissionGroupRepository;

    public PermissionService(
            PermissionRepository permissionRepository,
            PermissionMapper permissionMapper,
            PermissionGroupRepository permissionGroupRepository
    ) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
        this.permissionGroupRepository = permissionGroupRepository;
    }

    // Get all permissions
    public List<PermissionResponse> getList() {

        Sort sort = Sort.by(
                Sort.Direction.DESC,
                "id"
        );

        return permissionRepository
                .findAll(sort)
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    // Get permission by ID
    public PermissionResponse getById(Long id) {

        PermissionModel permissionModel = permissionRepository
                .findById(Math.toIntExact(id))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Permission not found with id: " + id
                        )
                );

        return permissionMapper.toResponse(permissionModel);
    }

    // Create permission
    public PermissionResponse create(PermissionRequest request) {

        // Find Permission Group
        PermissionGroupModel permissionGroupModel =
                permissionGroupRepository
                        .findById(Math.toIntExact(request.getPermissionGroupId()))
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Permission Group not found with id: "
                                                + request.getPermissionGroupId()
                                )
                        );

        // Convert request -> entity
        PermissionModel permissionModel =
                permissionMapper.toEntity(
                        request,
                        permissionGroupModel
                );

        // Save permission
        PermissionModel savedPermission =
                permissionRepository.save(permissionModel);

        // Convert entity -> response
        return permissionMapper.toResponse(savedPermission);
    }
}