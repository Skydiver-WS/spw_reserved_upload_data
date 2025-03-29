package ru.project.upload.data.dto.cient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {

    private Long clientId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}
