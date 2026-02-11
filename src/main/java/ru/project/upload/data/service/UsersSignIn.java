package ru.project.upload.data.service;

import ru.project.upload.data.dto.user.UserRs;

import java.util.List;

public interface UsersSignIn {

    List<UserRs> getUsersAdmin();

    void signInUsers(List<UserRs> users);
}
