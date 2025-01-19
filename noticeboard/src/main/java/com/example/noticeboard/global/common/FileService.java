package com.example.noticeboard.global.common;

import com.example.noticeboard.global.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public String saveFile(MultipartFile image) {
        try {
            String projectDir = Paths.get("").toAbsolutePath().toString();
            File directory = new File(projectDir, fileDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalName = image.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + "_" + originalName;
            image.transferTo(new File(directory, savedFileName));

            return savedFileName;
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }
}
