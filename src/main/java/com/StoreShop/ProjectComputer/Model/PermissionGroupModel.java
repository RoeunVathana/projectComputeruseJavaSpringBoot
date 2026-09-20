package com.StoreShop.ProjectComputer.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "permissionGroup")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    private  String description;
    private  Boolean active;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<PermissionModel> permissions;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        if(this.active == null) {
            this.active = true;
        }
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
