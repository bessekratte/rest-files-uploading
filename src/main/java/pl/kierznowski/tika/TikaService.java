package pl.kierznowski.tika;

import org.springframework.web.multipart.MultipartFile;

public interface TikaService {

    String parseFileIntoPlainText(MultipartFile file) ;
}
