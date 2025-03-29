package ru.project.upload.data.dto.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.project.upload.data.entity.Address;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    private String name;
    private Address address;
}
