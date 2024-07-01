package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class RegistrationDto {
    private Long id;

    @NotEmpty(message = "Username should not be empty.")
    private String username;

    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotEmpty(message = "Last Name should not be empty.")
    private String lastName;

    @NotEmpty(message = "Password should not be empty.")
    private String password;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    @NotEmpty(message = "Please upload a Photo for your profile.")
    private String profilePhoto;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdOn;
}
