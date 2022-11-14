package com.springboot.file.upload.repository;

import com.springboot.file.upload.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    static Optional<ImageData> findByName(String fileName) {
        return null;
    }
}

