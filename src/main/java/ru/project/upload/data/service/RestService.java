package ru.project.upload.data.service;


import org.springframework.http.ResponseEntity;
import ru.project.upload.data.dto.hotel.HotelResponse;
import ru.project.upload.data.dto.user.UserRs;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RestService {

    CompletableFuture<Void> rest(String url, Object object);

    ResponseEntity<UserRs> restSignIn (String url, Object object);

    void restUpload(String url, Object body, String token);

    HotelResponse getAllHotel(String url);

    boolean checkDeniedUser(String url, Object body);

}
