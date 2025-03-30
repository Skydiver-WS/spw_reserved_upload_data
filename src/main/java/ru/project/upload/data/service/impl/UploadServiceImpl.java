package ru.project.upload.data.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.project.upload.data.dto.hotel.HotelRequest;
import ru.project.upload.data.service.RestService;
import ru.project.upload.data.service.UploadService;

import java.io.File;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {


    private final ObjectMapper objectMapper;
    private final RestService restService;



    @Override
    @SneakyThrows
    public void uploadHotel() {
        List<HotelRequest> hotels = objectMapper.readValue(new File("src/main/resources/upload-data/hotels-v4.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, HotelRequest.class));

        hotels.forEach(h -> {
            restService.rest("http://localhost:8082/api/v1/hotel", h);
        });

    }

    @Override
    @SneakyThrows
    public void uploadRoom() {
        List<Object> rooms = objectMapper.readValue(new File("src/main/resources/upload-data/hotel_rooms.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
        log.info("Rooms: {}", rooms.size());
        Thread.sleep(5000);
        rooms.forEach(r -> {
            restService.rest("http://localhost:8082/api/v1/room", r);
        });

    }
}
