package com.cloudshare.service.impl;

import com.cloudshare.service.FileService;
import com.cloudshare.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileStorageService fileStorageService;

    @Override
    public String upload(MultipartFile file){
        try{
            String path = fileStorageService.store(file);
            return "File upload successfully: " + path;
        } catch (IOException e){
            throw new RuntimeException("Unable to upload file", e);
        }

    }
}
