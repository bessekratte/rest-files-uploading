package pl.kierznowski.lucene.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kierznowski.file.WebFile;
import pl.kierznowski.lucene.LuceneProperties;

import java.io.IOException;

@Component
public class WebFilesIndexerService implements FilesIndexerService {


    private LuceneProperties luceneProperties;

    @Autowired
    public WebFilesIndexerService(LuceneProperties luceneProperties) {
        this.luceneProperties = luceneProperties;
    }

    @Override
    public void indexFile (WebFile file) {

        try {
            IndexWriterConfig config = new IndexWriterConfig(luceneProperties.getAnalyzer());
            IndexWriter iwriter = new IndexWriter(luceneProperties.getRamDirectory(), config);
            Document doc = new Document();
            doc.add(new Field("title", file.getTitle(), TextField.TYPE_STORED));
            doc.add(new Field("author", file.getAuthor(), TextField.TYPE_STORED));
            doc.add(new Field("describe", file.getDescribe(), TextField.TYPE_STORED));
            doc.add(new Field("content", file.getContent(), TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            iwriter.close();
        } catch (IOException e){
            // TODO: 14.01.19 add special exception class
            e.printStackTrace();
        }


    }
}
