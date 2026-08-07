package com.cloudshare.service.storage;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String store(MultipartFile file, String storedFileName) throws IOException;
    Resource loadAsResource(String fileName);
    void delete(String fileName);
}
