package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Username should not be empty.")
    private String username;

    private String name;

    private String lastName;

    @NotEmpty(message = "Password should not be empty.")
    private String password;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    private String profilePhoto;

    private Boolean active = true;

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
