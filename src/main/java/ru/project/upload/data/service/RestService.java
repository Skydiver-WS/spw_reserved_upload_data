package ru.project.upload.data.service;


import java.util.concurrent.CompletableFuture;

public interface RestService {

    CompletableFuture<Void> rest(String url, Object object);
}
