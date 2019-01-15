package pl.kierznowski.storage;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;


@Component
@Data
public class StorageProperties {

    private Path filesDirectory = Paths.get("upload-files");
    private String[] allowedFileExtensions = new String[]{"doc", "docx", "odt", "rtf", "pdf", "html"};

}
