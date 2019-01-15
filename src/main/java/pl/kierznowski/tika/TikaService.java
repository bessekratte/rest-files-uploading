package pl.kierznowski.tika;

import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TikaService {

    String parseFileIntoPlainText(MultipartFile file) ;

}
