package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class UserDto {
    private Long id;

    @NotEmpty(message = "Username should not be empty.")
    private String username;

    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotEmpty(message = "Last name should not be empty.")
    private String lastName;

    @NotEmpty(message = "Password should not be empty.")
    private String password;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    @NotEmpty(message = "Profile photo should not be empty.")
    private String profilePhoto;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public String getFormattedCreatedOn(){
        return formatDateTime(createdOn);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if(dateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
            return dateTime.format(formatter);
        }
        return "";
    }
}
