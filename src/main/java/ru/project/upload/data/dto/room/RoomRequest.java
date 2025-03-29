package ru.project.upload.data.dto.room;

import com.sun.jdi.ClassType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.project.upload.data.dto.type.ClassRoomType;
import ru.project.upload.data.dto.type.SortType;
import ru.project.upload.data.dto.type.StatusType;
import ru.project.upload.data.entity.Photo;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    private Integer id;

    private Long hotelId;

    private Long numberApart;

    private String description;

    private StatusType statusType;

    private ClassRoomType classRoomType;

    private List<Photo> photoList;

    private RoomSearchRequest roomSearch;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RoomSearchRequest {
        @NotNull
        private Long hotelId;
        @NotNull
        private String hotelName;
        @NotNull
        private Date startReserved;
        @NotNull
        private Date endReserved;
        private SortType sortCoast;
        private Double coast;
        private ClassType classType;
    }
}
