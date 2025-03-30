package ru.project.upload.data.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.project.upload.data.service.RestService;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Profile({"kafka"})
public class KafkaSenderServiceImpl implements RestService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public CompletableFuture<Void> rest(String url, Object object) {
        if (url.equals("http://localhost:8082/api/v1/hotel")){
            sendHotel(object);
        } else if (url.equals("http://localhost:8082/api/v1/room")){
            sendRoom(object);
        }
        return null;
    }

    @SneakyThrows
    private void sendHotel(Object o){
        kafkaTemplate.send("create-hotel", objectMapper.writeValueAsString(o));
    }

    @SneakyThrows
    private void sendRoom(Object o){
        kafkaTemplate.send("create-room", objectMapper.writeValueAsString(o));
    }
}
