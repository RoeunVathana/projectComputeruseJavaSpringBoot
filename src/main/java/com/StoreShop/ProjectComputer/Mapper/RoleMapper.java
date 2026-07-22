package com.StoreShop.ProjectComputer.Mapper;

import com.StoreShop.ProjectComputer.DTO.Role.RoleRequest;
import com.StoreShop.ProjectComputer.DTO.Role.RoleResponse;
import com.StoreShop.ProjectComputer.Model.RoleModel;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleModel entity(RoleRequest dto){
        return RoleModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public RoleResponse toResponse(RoleModel entity){
        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .permission(entity.getPermissions())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
