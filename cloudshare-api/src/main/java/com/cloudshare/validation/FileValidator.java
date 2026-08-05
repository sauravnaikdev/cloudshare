package com.cloudshare.validation;

import com.cloudshare.exception.FileStorageException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileValidator {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //10 MB

    private static final List<String> ALLOWED_TYPES = List.of(
            "pdf",
            "png",
            "jpg",
            "jpeg",
            "txt",
            "docx"
    );

    public void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("File cannot be empty.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileStorageException("Maximum file size is 10 MB.");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || !fileName.contains(".")) {
            throw new FileStorageException("Invalid file.");
        }

        String extension =
                fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!ALLOWED_TYPES.contains(extension)) {
            throw new FileStorageException(
                    "Only pdf, png, jpg, jpeg, txt and docx files are allowed."
            );
        }

    }
}