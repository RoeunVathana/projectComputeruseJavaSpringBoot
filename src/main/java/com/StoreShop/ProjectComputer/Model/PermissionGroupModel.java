package com.StoreShop.ProjectComputer.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
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
