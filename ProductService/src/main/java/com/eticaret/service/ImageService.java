package com.eticaret.service;

import com.eticaret.dto.request.CreateProductRequestDto;
import com.eticaret.dto.request.SaveImageRequestDto;
import com.eticaret.dto.response.GetImageResponseDto;
import com.eticaret.repository.IImageRepository;
import com.eticaret.repository.entity.Image;
import com.eticaret.repository.entity.Product;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService extends ServiceManager<Image,String> {
    private final IImageRepository repository;
    public ImageService(IImageRepository repository){
        super(repository);
        this.repository = repository;
    }

     public void save(SaveImageRequestDto dto){
        dto.getImageUrls().forEach(item -> {
            Image image = Image.builder()
                            .productId(dto.getProductId())
                            .imageUrl(item)
                            .build();
            repository.save(image);
        });
     }

     public GetImageResponseDto findImagesById(String productId){
        List<Image> imageList = repository.findAllByProductId(productId);
        GetImageResponseDto dto = new GetImageResponseDto();
        if(imageList.isEmpty())
            throw new RuntimeException("IMAGE NOT FOUND");
        imageList.forEach(image -> {
            dto.getImageUrls().add(image.getImageUrl());
        });
        return dto;
     }
}
