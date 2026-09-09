package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpRequest;
import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpResponse;
import com.StoreShop.ProjectComputer.Mapper.PermissionGroupMapper;
import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import com.StoreShop.ProjectComputer.Repository.PermissionGroupRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionGroupService {
    final PermissionGroupRepository permissionGroupRepository;
    final PermissionGroupMapper permissionGroupMapper;

    public  PermissionGroupService(PermissionGroupRepository permissionGroupRepository, PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.permissionGroupMapper = permissionGroupMapper;
    }


    public List<PermissionGpResponse> getList() {

        Sort sort = Sort.by(
                Sort.Direction.DESC,
                "id"
        );

        return permissionGroupRepository.findAll(sort)
                .stream()
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

    public PermissionGpResponse updatePermissionGp(PermissionGpRequest request ,int id) {
         PermissionGroupModel permissionGroupModel = permissionGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission Not Found!"));
         permissionGroupModel.setName(request.getName());
         permissionGroupModel.setDescription(request.getDescription());
         permissionGroupModel.setActive(request.isActive());
         PermissionGroupModel save = permissionGroupRepository.save(permissionGroupModel);
        return  permissionGroupMapper.toResponse(save);
    }

    public PermissionGpResponse deletePermissionGp(int id) {
        PermissionGroupModel permissionGroupModel = permissionGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission Not Found!"));
        PermissionGroupModel save = permissionGroupRepository.save(permissionGroupModel);
        return permissionGroupMapper.toResponse(save);
    }

}
