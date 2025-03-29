package ru.project.upload.data.entity;


import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Long id;

    private String name;

    private Set<Hotel> hotels;

}
