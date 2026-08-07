package com.cloudshare.controller;

import com.cloudshare.dto.ApiResponse;
import com.cloudshare.model.FileMetadata;
import com.cloudshare.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<FileMetadata>> uploadFile(@RequestParam("file") MultipartFile file){
        FileMetadata metadata = fileService.uploadFile(file);
        ApiResponse<FileMetadata> response = new ApiResponse<>(true, "File uploaded successfully", metadata, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        Resource resource = fileService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFilename()+"\"").body(resource);
    }
}
