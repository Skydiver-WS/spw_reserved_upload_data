package ru.project.upload.data.dto.hotel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.project.upload.data.entity.Photo;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private Double distance;
    private Double minCoast;
    private String rating;
    private Integer freeApart;
    private Integer countApart;
    private List<Photo> photos;
    private String errorMessage;
    private String message;
}