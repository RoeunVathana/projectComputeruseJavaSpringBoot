package com.StoreShop.ProjectComputer.DTO.Permission;

import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    private String name;
    private String description;
    private Boolean active;
    private PermissionGroupModel permissionGroup;
    private Long permissionGroupId;
}
