package pl.kierznowski.lucene;

import lombok.Data;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class LuceneProperties {

    Directory ramDirectory = new RAMDirectory();
    Analyzer analyzer = new StandardAnalyzer();
    Map<String, Float> weightMap = new HashMap<>();
    String[] queryFields = new String[]{"title", "author", "describe", "content"};


    public LuceneProperties() {
        weightMap.put("title", 10f);
        weightMap.put("author", 10f);
        weightMap.put("describe", 5f);
        weightMap.put("content", 1f);
    }
}
