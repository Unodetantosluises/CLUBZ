package com.rungroup.web.dto;

import com.rungroup.web.models.Category;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @NotEmpty(message = "Post Title should not be Empty.")
    private String title;

    @Lob
    @NotEmpty(message = "Text Post should not be Empty.")
    private String textPost;

    private String photoUrl;

    private String author;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updateOn;

    private Set<String> categories;

    public String getFormattedCreatedOn(){
        return  formatDateTime(createdOn);
    }

    public String getFormattedUpdatedOn(){
        return formatDateTime(updateOn);
    }

    private String formatDateTime(LocalDateTime dateTime){
        if(dateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
            return  dateTime.format(formatter);
        }
        return "";
    }
}
