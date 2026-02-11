package ru.project.upload.data.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesType {

    ROLE_ADMIN("администратор сервиса"),
    ROLE_MANAGER_HOTEL("владелец отеля"),
    ROLE_EMPLOYEE("сотрудник отеля"),
    ROLE_CLIENT("клиент");

    private final String descriptionRole;
}
