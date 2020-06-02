package com.plagarism;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.misc.HighFreqTerms;
import org.apache.lucene.misc.TermStats;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneTest2 {
    final static String index = "index";
    final static String field = "text";

    public static void index() {
        try {
            Directory dir = FSDirectory.open(Paths.get(index));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            iwc.setOpenMode(OpenMode.CREATE);

            IndexWriter writer = new IndexWriter(dir, iwc);

            String[] lines = {
                    "lucene java lucene mark vilas",
                    "lucene",
                    "lucene an example lucene lucene",
                    "java lucene",
                    "java lucene vilas"
            };
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                Document doc = new Document();
                doc.add(new StringField("id", "" + i, Field.Store.YES));
                doc.add(new TextField(field, line.trim(), Field.Store.YES));
                writer.addDocument(doc);
            }

            System.out.println("indexed " + lines.length + " sentences");
            writer.close();
        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }

    public static void count() {
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
            int numTerms = 100;
            TermStats[] stats = HighFreqTerms.getHighFreqTerms(reader, numTerms, field, new HighFreqTerms.DocFreqComparator());
            for (TermStats termStats : stats) {
                String termText = termStats.termtext.utf8ToString();
                System.out.println(termText + " " + termStats.docFreq);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        index();
        count();
    }
}