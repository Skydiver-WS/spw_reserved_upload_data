package ru.project.upload.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.upload.data.service.UploadService;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @GetMapping("/hotel")
    public ResponseEntity<Void> uploadHotel(){
        uploadService.uploadHotel();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/room")
    public ResponseEntity<Void> uploadRoom(){
        uploadService.uploadRoom();
        return ResponseEntity.ok().build();
    }


}
