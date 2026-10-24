package com.StoreShop.ProjectComputer.DTO.User;

import com.StoreShop.ProjectComputer.Model.UserProfileModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
public class LoginRequest {
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 16, message = "Phone must be 8-16 characters")
    private String phone;

    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

}
