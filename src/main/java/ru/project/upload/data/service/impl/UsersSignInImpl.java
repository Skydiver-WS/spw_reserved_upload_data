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
import ru.project.upload.data.service.UsersSignIn;

import java.io.File;
import java.util.List;

import static ru.project.upload.data.service.impl.UploadServiceImpl.respUsersList;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsersSignInImpl implements UsersSignIn {

    private final RestService restService;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public List<UserRs> getUsersAdmin() {
        List<UserRs> users = objectMapper.readValue(new File("src/main/resources/upload-data/users_1000.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, UserRs.class));
       return users.stream()
                .filter(u -> u.getRole().stream()
                        .anyMatch(r -> r.getRolesType().equals(RolesType.ROLE_ADMIN)))
                .toList();
    }

    @Override
    public void signInUsers(List<UserRs> users) {
        users.forEach(u -> {
            ResponseEntity<UserRs> rs = restService.restSignIn("http://rest-app-route-hotel-apps.apps-crc.testing/api/v1/user/sing-in", UserRqDb.builder()
                    .username(u.getUsername())
                    .password(u.getPassword())
                    .build());

            HttpHeaders headers = rs.getHeaders();
            List<String> cookies = headers.get(HttpHeaders.SET_COOKIE);
            UserRs body = getUserRs(rs, cookies);
            respUsersList.add(body);
        });
    }

    private static UserRs getUserRs(ResponseEntity<UserRs> rs, List<String> cookies) {
        UserRs body = rs.getBody();
        if (cookies != null) {
            for (String cookieHeader : cookies) {
                // Ищем куку token
                if (cookieHeader.startsWith("token=")) {
                    // Извлекаем значение: token=value; остальные параметры
                    String[] parts = cookieHeader.split(";")[0].split("=");
                    if (parts.length >= 2) {
                        body.setToken(parts[1]);
                    }
                }
            }
        }
        return body;
    }
}
