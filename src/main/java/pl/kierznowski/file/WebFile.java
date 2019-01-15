package pl.kierznowski.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.file.Path;

@Data
@AllArgsConstructor
public class WebFile {

    private String title;
    private String author;
    private String describe;
    private String content;
    private Path path;
}
