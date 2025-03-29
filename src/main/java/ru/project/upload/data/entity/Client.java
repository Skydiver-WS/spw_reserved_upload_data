package ru.project.upload.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private Integer series;
    private Integer number;
    private List<Photo> documents;
}
