package pl.kierznowski.lucene.search.mapper;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LuceneSearchResultsMapper implements SearchResultsMapper {

    @Override
    public List<String> mapResults(List<Document> results) {
        return results.stream()
                .map(document -> document.get("name"))
                .collect(Collectors.toList());
    }
}