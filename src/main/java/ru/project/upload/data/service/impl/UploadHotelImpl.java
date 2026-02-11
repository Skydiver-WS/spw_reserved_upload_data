package ru.project.upload.data.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.project.upload.data.dto.hotel.HotelRq;
import ru.project.upload.data.dto.user.RolesType;
import ru.project.upload.data.dto.user.UserRqDb;
import ru.project.upload.data.dto.user.UserRs;
import ru.project.upload.data.service.RestService;
import ru.project.upload.data.service.UploadHotelService;
import ru.project.upload.data.service.UsersSignIn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static ru.project.upload.data.service.impl.UploadServiceImpl.respUsersList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadHotelImpl implements UploadHotelService {

    private final RestService restService;
    private final ObjectMapper objectMapper;
    private final UsersSignIn usersSignIn;


    @Override
    @SneakyThrows
    public void uploadHotels() {
        if (!respUsersList.isEmpty()) {
            respUsersList.clear();
        }
        List<HotelRq> hotels = objectMapper.readValue(new File("src/main/resources/upload-data/hotels_10000.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, HotelRq.class));

        List<UserRs> usersAdmin = usersSignIn.getUsersAdmin();
        //Авторизация и получение токена
        usersSignIn.signInUsers(usersAdmin);


// Загрузка отеля
        hotels.forEach(h -> {
            int randomNum = ThreadLocalRandom.current().nextInt(0, usersAdmin.size());
            log.info("Users count {}, select index {}", usersAdmin.size(), randomNum);
            UserRs user = respUsersList.get(randomNum);
            h.setUsersRq(List.of(HotelRq.UserRq.builder()
                    .userId(user.getUserId())
                    .build()));

            restService.restUpload("https://localhost:8443/api/v1/hotel", h, user.getToken());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        respUsersList.clear();

    }
}
