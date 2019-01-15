package pl.kierznowski.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kierznowski.lucene.search.FilesSearcherService;
import pl.kierznowski.lucene.search.mapper.SearchResultsMapper;

import java.io.IOException;

@Controller
public class FileSearchController {

    private final FilesSearcherService searcherService;
    private final SearchResultsMapper resultsMapper;

    @Autowired
    public FileSearchController(FilesSearcherService searcherService,
                                SearchResultsMapper resultsMapper) {

        this.searcherService = searcherService;
        this.resultsMapper = resultsMapper;
    }

    @GetMapping("/search")
    public String listUploadedFiles(Model model) throws IOException {
        return "searchForm";
    }

    @PostMapping("/search")
    public String lookForFile(@RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String describe,
                              @RequestParam String content,
                              Model model) {

        model.addAttribute("files", resultsMapper.mapResults(searcherService.lookForFile(title, author, describe, content)));
        return "searchForm";
    }
}

