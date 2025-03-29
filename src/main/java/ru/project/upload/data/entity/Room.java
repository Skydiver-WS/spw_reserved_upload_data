package ru.project.upload.data.entity;


import lombok.*;
import ru.project.upload.data.dto.type.ClassRoomType;
import ru.project.upload.data.dto.type.StatusType;

import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {


    private Long id;

    private Long numberApart;

    private Double coast;

    private String description;

    private StatusType status = StatusType.FREE;

    private Date startReserved;

    private Date endReserved;

    private List<Photo> photoList;

    private StatusType statusType = StatusType.FREE;

    private ClassRoomType classRoomType;

    private Hotel hotel;

}
