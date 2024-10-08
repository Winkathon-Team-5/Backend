package com.github.winkathon.tripee.domain.upload.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.tripee.domain.upload.repository.ImageRepository;
import com.github.winkathon.tripee.domain.upload.schema.Image;
import com.github.winkathon.tripee.domain.user.schema.User;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class UploadUtil {

    private final ImageRepository imageRepository;

    private final Path uploadFolder = Path.of("uploads");

    @PostConstruct
    @SneakyThrows(IOException.class)
    public void init() {

        if (Files.notExists(uploadFolder)) {

            Files.createDirectories(uploadFolder);
        }
    }

    @SneakyThrows(IOException.class)
    public Image upload(User user, MultipartFile file) {

        assert file.getOriginalFilename() != null;
        String fileExtension = extractFileExtension(file.getOriginalFilename());

        String fileName = UUID.randomUUID() + fileExtension;
        Path filePath = Path.of(uploadFolder.toString(), fileName);

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));

        outputStream.write(file.getBytes());
        outputStream.close();

        Image image = Image.builder()
                .owner(user)
                .fileName(fileName)
                .build();

        return imageRepository.save(image);
    }

    private String extractFileExtension(String fileName) {

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
