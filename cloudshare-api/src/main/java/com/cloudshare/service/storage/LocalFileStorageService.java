package com.cloudshare.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFileStorageService implements FileStorageService {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    public String store (MultipartFile file, String storedFileName) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)){
            Files.createDirectory(uploadPath);
        }
        Path filePath = uploadPath.resolve(storedFileName);
        System.out.println("Saving File At : " + filePath.toAbsolutePath());
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            System.out.println("Looking For : " + filePath.toAbsolutePath());
            Resource resource = new UrlResource(filePath.toUri());
            System.out.println("Exists : " + resource.exists());
            if (resource.exists()) {
                return resource;
            }
            throw new RuntimeException("File not found.");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to read file.", e);
        }

    }

    @Override
    public void delete(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Unable to delete file.");
        }

    }
}
