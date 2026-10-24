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
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDate createdAt;

    private UserProfileModel profile;
    List<String> roles;
    List<String> permissions;
    String accessToken;
    String refreshToken;
}
