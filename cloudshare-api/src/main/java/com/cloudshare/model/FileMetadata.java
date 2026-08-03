package com.cloudshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadata {

    private String id;

    private String originalFileName;

    private String storedFileName;

    private String contentType;

    private long size;

    private String storagePath;

    private LocalDateTime uploadedAt;

    private String status;
}
