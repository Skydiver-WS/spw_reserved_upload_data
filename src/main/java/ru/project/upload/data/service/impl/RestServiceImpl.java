package ru.project.upload.data.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.project.upload.data.dto.hotel.HotelResponse;
import ru.project.upload.data.dto.user.UserRqDb;
import ru.project.upload.data.dto.user.UserRs;
import ru.project.upload.data.service.RestService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile({"rest"})
public class RestServiceImpl implements RestService {
    private final RestClient restClient;
    @Override
    //@Async
    public CompletableFuture<Void> rest(String url, Object data) {
        log.info("Upload object: {}", data);
        restClient.post()
                .uri(url)
                .body(data)
                .retrieve()
                .toBodilessEntity();
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public ResponseEntity<UserRs> restSignIn(String url, Object object) {
        log.info("SignIn {}", object);
        return restClient
                .post()
                .uri(url)
                .body(object)
                .retrieve()
                .toEntity(UserRs.class);

    }

    @Override
    @Async
    public void restUpload(String url, Object body, String token) {
        log.info("Upload: {}", body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + token);

        try {
            restClient.post()
                    .uri(url)
                    .headers(h -> h.addAll(headers))
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e){
            log.error("Error: ", e);
        }

    }

    @Override
    public HotelResponse getAllHotel(String url) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(HotelResponse.class);
    }

    @Override
    public boolean checkDeniedUser(String url, Object body) {
        return Boolean.TRUE.equals(restClient.post()
                .uri(url)
                .body(body)
                .retrieve()
                .body(Boolean.class));
    }


}
