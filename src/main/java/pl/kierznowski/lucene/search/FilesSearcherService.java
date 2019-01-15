package pl.kierznowski.lucene.search;

import pl.kierznowski.file.WebFile;

import java.util.List;

public interface FilesSearcherService {

    List lookForFile(String title, String author, String describe, String content);
}
