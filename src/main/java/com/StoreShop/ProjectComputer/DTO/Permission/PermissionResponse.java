package com.StoreShop.ProjectComputer.DTO.Permission;

import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Permission;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Long id;
    private Long permissionGroupId;
    private String name;
    private String description;
    private String permissionGroupName;
    private Boolean active;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private PermissionGroupModel permissionGroup;
}
