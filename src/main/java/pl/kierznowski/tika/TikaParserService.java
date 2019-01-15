package pl.kierznowski.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class TikaParserService implements TikaService {

    @Override
    public String parseFileIntoPlainText(MultipartFile file) {
// TODO: 14.01.19 correct expcetion classes
        try {
            InputStream stream = file.getInputStream();
            Tika tika = new Tika();
            return tika.parseToString(stream);
        } catch (TikaException tikeException) {
            tikeException.printStackTrace();
            return "";
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";

        }

    }


}