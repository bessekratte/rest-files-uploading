package pl.kierznowski.lucene.search.mapper;

import org.apache.lucene.document.Document;

import java.util.List;

public interface SearchResultsMapper {

    List<String> mapResults(List<Document> results);

}
