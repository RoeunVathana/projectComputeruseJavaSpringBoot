package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpRequest;
import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpResponse;
import com.StoreShop.ProjectComputer.Mapper.PermissionGroupMapper;
import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import com.StoreShop.ProjectComputer.Model.PermissionModel;
import com.StoreShop.ProjectComputer.Repository.PermissionGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionGroupService {
    final PermissionGroupRepository permissionGroupRepository;
    final PermissionGroupMapper permissionGroupMapper;

    public PermissionGroupService(PermissionGroupRepository permissionGroupRepository, PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.permissionGroupMapper = permissionGroupMapper;
    }


    public List<PermissionGpResponse> getList() {
        return permissionGroupRepository.findAll().stream()
                .map(permissionGroupMapper::toResponse)
                .toList();
    }

    public PermissionGpResponse getById(int id) {
        PermissionGroupModel permissionGroupModel = permissionGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission Not Found!"));
        return permissionGroupMapper.toResponse(permissionGroupModel);
    }

    public PermissionGpResponse createPermissionGp(PermissionGpRequest request) {

        if(request.getName().isEmpty()){
            throw new RuntimeException("Permission Name Empty!");
        }

        PermissionGroupModel permissionGroupModel = permissionGroupMapper.toEntity(request);

        PermissionGroupModel save = permissionGroupRepository.save(permissionGroupModel);
        return permissionGroupMapper.toResponse(save);
    }
}
