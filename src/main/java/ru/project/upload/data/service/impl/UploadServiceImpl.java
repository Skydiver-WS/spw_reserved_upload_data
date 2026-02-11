package ru.project.upload.data.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.upload.data.dto.hotel.HotelRq;
import ru.project.upload.data.dto.user.UserRs;
import ru.project.upload.data.service.RestService;
import ru.project.upload.data.service.UploadHotelService;
import ru.project.upload.data.service.UploadRooms;
import ru.project.upload.data.service.UploadService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {


    private final ObjectMapper objectMapper;
    private final RestService restService;
    private final UploadHotelService hotelService;
    private final UploadRooms uploadRooms;

    public final static List<UserRs> respUsersList = new ArrayList<>();


    @Override
    @SneakyThrows
    public void uploadHotel() {
        hotelService.uploadHotels();


    }

    @Override
    @SneakyThrows
    public void uploadRoom() {
        uploadRooms.uploadRoom();


    }

    @Override
    @SneakyThrows
    public void uploadUsers() {
        List<Object> users = objectMapper.readValue(new File("src/main/resources/upload-data/users_1000.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
        users.forEach(u -> {
            try {
                restService.rest("https://localhost:8443/api/v1/user/create", u);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
