package com.cloudshare.service;

import com.cloudshare.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileMetadata uploadFile(MultipartFile file);
    Resource downloadFile(String fileName);
}
