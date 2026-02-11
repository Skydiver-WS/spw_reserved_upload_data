package ru.project.upload.data.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRqDb {

    private String username;
    private String password;
}
