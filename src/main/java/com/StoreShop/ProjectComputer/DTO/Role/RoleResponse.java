package com.StoreShop.ProjectComputer.DTO.Role;

import com.StoreShop.ProjectComputer.Model.PermissionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private Set<PermissionModel> permission;

}
