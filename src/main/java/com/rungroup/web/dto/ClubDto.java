package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
public class ClubDto {
    private Long id;

    @NotEmpty(message = "Club Title should not be empty")
    private String title;

    @NotEmpty(message = "Club Photo Link should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Club Content should not be empty")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    private List<EventDto> events;

    public String getFormattedCreatedOn(){
        return formatDateTime(createdOn);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
            return dateTime.format(formatter);
        }
        return "";
    }
}
