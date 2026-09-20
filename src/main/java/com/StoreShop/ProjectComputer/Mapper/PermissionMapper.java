package com.StoreShop.ProjectComputer.Mapper;

import com.StoreShop.ProjectComputer.DTO.Permission.PermissionRequest;
import com.StoreShop.ProjectComputer.DTO.Permission.PermissionResponse;
import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import com.StoreShop.ProjectComputer.Model.PermissionModel;
import org.springframework.stereotype.Component;
@Component
public class PermissionMapper {
    public PermissionModel toEntity (PermissionRequest dto, PermissionGroupModel permissionGroup) {
        return PermissionModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .permissionGroup(permissionGroup)
                .build();
    }
    public PermissionResponse toResponse(PermissionModel dto){
        return PermissionResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
//                .permissionGroupName(dto.getPermissionGroup().getName())
//                .permissionGroupId(dto.getPermissionGroup().getId())
                .permissionGroup(dto.getPermissionGroup())
                .build();
    }
}
