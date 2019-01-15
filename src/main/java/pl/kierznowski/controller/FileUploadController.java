package pl.kierznowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pl.kierznowski.file.WebFile;
import pl.kierznowski.file.WebFileService;
import pl.kierznowski.lucene.index.FilesIndexerService;
import pl.kierznowski.lucene.search.FilesSearcherService;
import pl.kierznowski.storage.exception.StorageFileNotFoundException;
import pl.kierznowski.storage.StorageService;

import java.io.IOException;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final WebFileService webFileService;
    private final FilesIndexerService indexerService;
    private final FilesSearcherService searcherService;

    @Autowired
    public FileUploadController(StorageService storageService,
                                WebFileService webFileService,
                                FilesIndexerService indexerService,
                                FilesSearcherService searcherService) {
        this.storageService = storageService;
        this.webFileService = webFileService;
        this.indexerService = indexerService;
        this.searcherService = searcherService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("title") String title,
                                   @RequestParam("author") String author,
                                   @RequestParam("describe") String describe) {

        storageService.store(file);
        indexerService.indexFile(webFileService.createWebFileFromMultipartFile(title, author, describe, file));
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}