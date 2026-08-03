package com.cloudshare.service.impl;

import com.cloudshare.model.FileMetadata;
import com.cloudshare.service.FileService;
import com.cloudshare.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileMetadata uploadFile (MultipartFile file){
        return null;

    }
}
