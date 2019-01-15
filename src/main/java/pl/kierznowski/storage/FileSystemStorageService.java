package pl.kierznowski.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kierznowski.storage.exception.StorageException;

@Component
public class FileSystemStorageService implements StorageService {

    private final StorageProperties properties;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public void store(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, properties.getFilesDirectory().resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

}
