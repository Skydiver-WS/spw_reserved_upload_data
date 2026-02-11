package ru.project.upload.data.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.upload.data.dto.hotel.HotelResponse;
import ru.project.upload.data.dto.hotel.HotelRq;
import ru.project.upload.data.dto.room.RoomRequest;
import ru.project.upload.data.dto.user.UserRs;
import ru.project.upload.data.service.RestService;
import ru.project.upload.data.service.UploadRooms;
import ru.project.upload.data.service.UsersSignIn;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.project.upload.data.service.impl.UploadServiceImpl.respUsersList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadRoomsImpl implements UploadRooms {

    private final ObjectMapper objectMapper;
    private final RestService restService;
    private final UsersSignIn signIn;

    @Override
    @SneakyThrows
    public void uploadRoom() {
        signIn.signInUsers(signIn.getUsersAdmin());
        List<RoomRequest> rooms = objectMapper.readValue(new File("src/main/resources/upload-data/rooms_for_hotel.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, RoomRequest.class));
        Map<Long, List<RoomRequest>> roomsMap = rooms.stream().collect(Collectors.groupingBy(RoomRequest::getHotelId));
        rooms.clear(); // Комнат много что бы память не забивать чистим массив
        log.info("Get all hotels");

        log.info("Start");
        List<HotelResponse> hotels = new ArrayList<>();
        int page = 0;
        int size = 500;
        while (true) {
            List<HotelResponse> hotelsRs = restService
                    .getAllHotel("https://localhost:8443/api/v1/hotel" +
                            "?page=" + page +
                            "&size=" + size).getHotels();
            hotels.addAll(hotelsRs);
            log.info("Size {}", hotelsRs.size());
            if (hotelsRs.size() != size){
                break;
            }
            page++;
        }
        log.info("Continue");
        hotels.forEach(h -> {
            //Получаем владельца отеля, что бы взять токен авторизации
            log.info("Get manager hotel {}", h);
            Optional<UserRs> userRsOptional = respUsersList.stream()
                    .filter(u -> restService.checkDeniedUser("http://localhost:8081/booking-app/api/v1/hotel/check-denied",
                            HotelRq.builder()
                                    .id(h.getId())
                                    .userRq(HotelRq.UserRq.builder()
                                            .userId(u.getUserId())
                                            .build()).build()))
                    .findFirst();
            if (userRsOptional.isPresent()) {
                UserRs userRs = userRsOptional.get();
                //Получаем список комнат по id отеля
                List<RoomRequest> roomsHotel = roomsMap.get(h.getId());
                //Загружаем комнаты
                roomsHotel.forEach(r -> {
                    try {
                        log.info("Upload room");
                        restService.restUpload("https://localhost:8443/api/v1/room", r, userRs.getToken());
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        roomsMap.clear();
    }
}
