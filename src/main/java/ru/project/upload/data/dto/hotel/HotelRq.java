package ru.project.upload.data.dto.hotel;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.project.upload.data.dto.city.CityRequest;
import ru.project.upload.data.dto.type.SortType;
import ru.project.upload.data.entity.Photo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRq {

    private Long id;
    private String name;
    private String description;
    private String address;
    private Double distance;
    private Double rating;
    private List<Photo> photos;
    private String city;
    private HotelSearchRequest hotelSearch;
    private List<UserRq> usersRq;
    private UserRq userRq;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HotelSearchRequest {
        private String city;
        private String hotelName;
        private Date startReserved;
        private Date endReserved;
        private SortType sortCoast;
        private Long coastMin;
        private Long coastMax;
        private SortType sortRating;
        private Double rating;
        private SortType sortDistance;
        private Double distance;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRq{
        @NotNull
        private String userId;
    }
}

