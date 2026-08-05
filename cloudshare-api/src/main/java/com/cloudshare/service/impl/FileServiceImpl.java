package com.cloudshare.service.impl;

import com.cloudshare.exception.FileStorageException;
import com.cloudshare.model.FileMetadata;
import com.cloudshare.service.FileService;
import com.cloudshare.service.storage.FileStorageService;
import com.cloudshare.validation.FileValidator;
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
    private final FileValidator fileValidator;

    //UPLOAD File logic
    @Override
    public FileMetadata uploadFile (MultipartFile file) {
//        throw new FileStorageException("Testing Global Exception");
        fileValidator.validate(file);
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
        throw new FileStorageException("Unable to upload file", e);

        }

    }

    //DOWNLOAD File logic
    @Override
    public Resource downloadFile(String fileName) {

        return fileStorageService.loadAsResource(fileName);

    }
}
