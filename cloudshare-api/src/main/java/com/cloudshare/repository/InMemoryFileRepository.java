package com.cloudshare.repository;

import com.cloudshare.model.FileMetadata;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFileRepository {

    private final Map<String, FileMetadata> fileStore = new HashMap<>();

    public void save(FileMetadata metadata) {
        fileStore.put(metadata.getId(), metadata);
    }

    public Optional<FileMetadata> findById(String id) {
        return Optional.ofNullable(fileStore.get(id));
    }

    public List<FileMetadata> findAll() {
        return new ArrayList<>(fileStore.values());
    }

    public void delete(String id) {
        fileStore.remove(id);
    }

    public boolean existsById(String id) {
        return fileStore.containsKey(id);
    }

}
