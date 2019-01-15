package pl.kierznowski.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.kierznowski.lucene.index.WebFilesIndexerService;
import pl.kierznowski.tika.TikaService;

import java.nio.file.Paths;

@Component
public class WebFileService {

    private TikaService tika;
//    private FilesIndexerService indexer;

    @Autowired
    public WebFileService(TikaService tika, WebFilesIndexerService indexer) {
        this.tika = tika;
//        this.indexer = indexer;
    }

    public WebFile createWebFileFromMultipartFile(String title, String author, String describe, MultipartFile file) {
        String content = tika.parseFileIntoPlainText(file);
        return new WebFile(title, author, describe, content, Paths.get(file.getOriginalFilename()));
    }

}
