package com.StoreShop.ProjectComputer.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Setter
@Getter

@Table(name = "permission")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionGroupId")
    private PermissionGroupModel permissionGroup;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();

        if (this.active == null) {
            this.active = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}