package com.StoreShop.ProjectComputer.DTO.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCategory {
    Long id;
    String name;
    String description;
    Boolean active;
    LocalDate updatedAt;
    LocalDate createdAt;
}
