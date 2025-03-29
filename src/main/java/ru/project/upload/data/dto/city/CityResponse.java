package ru.project.upload.data.dto.city;

import lombok.*;
import ru.project.upload.data.entity.Address;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {

    private Long id;

    private String name;

    private List<Address> address;
}
