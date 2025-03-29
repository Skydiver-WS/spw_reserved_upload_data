package ru.project.upload.data.entity;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;

    private String name;

    private String description;

    private Double distance;

    private Double rating;


    private Integer freeApart = 0;

    private Integer countApart = 0;


    private Set<City> cityList;


    private List<Room> roomList;


    private List<Address> addressList;

    private List<Photo> photos;

}
