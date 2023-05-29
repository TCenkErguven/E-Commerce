package com.eticaret.controller;

import com.eticaret.repository.entity.Image;
import com.eticaret.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping("/find-all")
    public ResponseEntity<List<Image>> findAll(){
        return ResponseEntity.ok(imageService.findAll());
    }


}
