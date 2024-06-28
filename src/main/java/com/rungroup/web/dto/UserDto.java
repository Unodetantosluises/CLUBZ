package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotEmpty(message = "Last Name should not be empty.")
    private String lastName;

    private String role;

    @NotEmpty(message = "Please upload a Photo for your profile.")
    private String profilePhoto;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdOn;
}
