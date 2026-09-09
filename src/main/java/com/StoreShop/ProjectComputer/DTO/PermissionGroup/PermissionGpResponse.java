package com.StoreShop.ProjectComputer.DTO.PermissionGroup;

import com.StoreShop.ProjectComputer.Model.PermissionGroupModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.SequencedCollection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGpResponse{
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
