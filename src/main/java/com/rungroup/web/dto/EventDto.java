package com.rungroup.web.dto;

import com.rungroup.web.models.Club;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class EventDto {
    private Long id;

    @NotEmpty(message = "Event Name should not be Empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Start Time should be in the future or present")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "End Time should be in the future or present")
    private LocalDateTime endTime;

    @NotEmpty(message = "Event Type should not be Empty")
    private String type;

    @NotEmpty(message = "Event Photo Link should not be Empty")
    private String photoUrl;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    private Club club;

    public String getFormattedCreatedOn() {
        return formatDateTime(createdOn);
    }

    public String getFormattedStartTime(){
        return formatDateTime(startTime);
    }

    public String getFormattedEndTime(){
        return  formatDateTime(endTime);
    }

    private String formatDateTime(LocalDateTime dateTime){
        if (dateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
            return dateTime.format(formatter);
        }
        return "";
    }
}
