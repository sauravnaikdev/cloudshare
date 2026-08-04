package com.cloudshare.service.impl;

import com.cloudshare.model.FileMetadata;
import com.cloudshare.service.FileService;
import com.cloudshare.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileStorageService fileStorageService;

    @Override
    public FileMetadata uploadFile (MultipartFile file){
        try{
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.contains(".")){
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String id = UUID.randomUUID().toString();
            String storedFileName = id + extension;
            String storagePath = fileStorageService.store(file, storedFileName);
            return new FileMetadata(
                    id,
                    originalFileName,
                    storedFileName,
                    file.getContentType(),
                    file.getSize(),
                    storagePath,
                    LocalDateTime.now(),
                    "UPLOADED"
            );
        } catch (IOException e) {
        throw new RuntimeException("Unable to upload file", e);
        }

    }

    @Override
    public Resource downloadFile(String fileName) {

        return fileStorageService.loadAsResource(fileName);

    }
}
