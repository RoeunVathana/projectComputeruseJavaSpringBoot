package com.StoreShop.ProjectComputer.DTO.Role;

import com.StoreShop.ProjectComputer.Model.PermissionModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
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
public class RoleRequest {

    @NotEmpty(message = "name is required!")
    private String name;
    private String description;
    private Boolean status;
    private Long permission_id;
//    private Set<PermissionModel> permissions;
//    private PermissionModel permission;
}

