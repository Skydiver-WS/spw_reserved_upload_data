package ru.project.upload.data.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.project.upload.data.service.RestService;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile({"rest"})
public class RestServiceImpl implements RestService {
    private final RestClient restClient;
    @Override
    @Async
    public CompletableFuture<Void> rest(String url, Object data) {
        log.info("Upload object: {}", data);
        restClient.post()
                .uri(url)
                .body(data)
                .retrieve()
                .toBodilessEntity();
        return CompletableFuture.completedFuture(null);
    }
}
