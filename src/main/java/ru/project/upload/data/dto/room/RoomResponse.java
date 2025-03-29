package ru.project.upload.data.dto.room;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.project.upload.data.dto.type.ClassRoomType;
import ru.project.upload.data.dto.type.StatusType;
import ru.project.upload.data.entity.Photo;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {
    private Long id;

    private Long numberApart;

    private String description;

    private StatusType statusType;

    private ClassRoomType classRoomType;

    private List<Photo> photoList;

    private String errorMessage;
}
