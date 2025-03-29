package ru.project.upload.data.entity;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Address {

    private Long id;

    private String address;


    private City city;


}
