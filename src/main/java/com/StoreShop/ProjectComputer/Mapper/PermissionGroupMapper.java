package com.StoreShop.ProjectComputer.Mapper;

import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpRequest;
import com.StoreShop.ProjectComputer.DTO.PermissionGroup.PermissionGpResponse;
import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import org.springframework.stereotype.Component;

@Component
public class PermissionGroupMapper {

    // Request DTO -> Entity
    public PermissionGroupModel toEntity(PermissionGpRequest dto) {

        return PermissionGroupModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    // Entity -> Response DTO
    public PermissionGpResponse toResponse(PermissionGroupModel entity) {

        return PermissionGpResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}