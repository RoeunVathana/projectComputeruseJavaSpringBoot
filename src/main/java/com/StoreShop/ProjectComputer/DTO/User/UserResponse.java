package com.StoreShop.ProjectComputer.DTO.User;

import com.StoreShop.ProjectComputer.Model.UserProfileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDate createdAt;
    private UserProfileModel profile;
    private List<String> roles;
}
