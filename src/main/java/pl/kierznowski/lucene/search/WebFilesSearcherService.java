package pl.kierznowski.lucene.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kierznowski.lucene.LuceneProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebFilesSearcherService implements FilesSearcherService {

    private LuceneProperties luceneProperties;

    @Autowired
    public WebFilesSearcherService(LuceneProperties luceneProperties) {
        this.luceneProperties = luceneProperties;
    }

    @Override
    public List<?> lookForFile(String title, String author, String describe, String content) {

        try {
            DirectoryReader ireader = DirectoryReader.open(luceneProperties.getRamDirectory());
            IndexSearcher isearcher = new IndexSearcher(ireader);
            MultiFieldQueryParser parser = new MultiFieldQueryParser(
                    luceneProperties.getQueryFields(),
                    luceneProperties.getAnalyzer(),
                    luceneProperties.getWeightMap());

//            parser.setDefaultOperator(QueryParser.Operator.OR);
            Query query = parser.parse(
                    "title:" + title + " " +
                            "author:" + author + " " +
                            "describe:" + describe + " " +
                            "content:" + content);

            ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
            List<Document> documents = new ArrayList<>();
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
            }


            return documents;
        } catch (ParseException | IOException e) {
            // TODO: 14.01.19 popraw
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}