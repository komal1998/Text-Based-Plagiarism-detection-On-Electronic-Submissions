package com.plagarism.highlight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
 
public class HighlightAnalysisMain {
 
    
 
    public static String readFileString(String file) {
        StringBuffer text = new StringBuffer();
        try {
 
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(file)), "UTF8"));
            String line;
            while ((line = in.readLine()) != null) {
                text.append(line + "\r\n");
            }
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return text.toString();
    }
    
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
    	TextFragment getTextFragment=HighlightAnalysisMain.getTextFragment("id from above step", "C:\\temp\\content.txt", "Lucene Search Highlight Example - HowToDoInJavahowtodoinjava.com � lucene � lucene-search-highlight-example Lucene Search Highlight Steps. Search index with Query. Retrieve document text using document id from above step. Create TokenStream by document id and document text for the field. Use token stream and highlighter to get array of text fragments. Iterate the array and display it. It has highlighted search terms.");
    }
 
    @SuppressWarnings("deprecation")
    public static TextFragment getTextFragment(String phase,String fileName,String textData) {
    	  Analyzer analyzer = new StandardAnalyzer();
          IndexWriterConfig config = new IndexWriterConfig(
                analyzer);
        RAMDirectory ramDirectory = new RAMDirectory();
        IndexWriter indexWriter;
        Document doc = new Document(); // create a new document
 
        /**
         * Create a field with term vector enabled
         */
        FieldType type = new FieldType();
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        type.setStored(true);
        type.setStoreTermVectors(true);
        type.setTokenized(true);
        type.setStoreTermVectorOffsets(true);
 
        Field field = new Field("title",textData, type); //term vector enabled
        Field f = new TextField("content", readFileString(fileName),Field.Store.YES); 
        doc.add(field);
        doc.add(f);
        
        
        
 
        try {
            indexWriter = new IndexWriter(ramDirectory, config);
            indexWriter.addDocument(doc);
            indexWriter.close();
 
            IndexReader idxReader = DirectoryReader.open(ramDirectory);
            IndexSearcher idxSearcher = new IndexSearcher(idxReader);
            Query queryToSearch = new QueryParser("title", analyzer).parse(phase);
            TopDocs hits = idxSearcher
                    .search(queryToSearch, idxReader.maxDoc());
            
            
            for (int i = 0; i < hits.totalHits; i++) {
                Document doc1 = idxSearcher.doc(hits.scoreDocs[i].doc);//get the next  document
                System.out.println("Vilas "+doc.get("title"));
               }
            
            
            
            
            SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
            QueryScorer queryScorer = new QueryScorer(queryToSearch);
            Highlighter highlighter = new Highlighter(queryScorer);
          

            Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer,10000);

            highlighter.setMaxDocCharsToAnalyze(50000);
            highlighter.setTextFragmenter(fragmenter);
 
            System.out.println("reader maxDoc is " + idxReader.maxDoc());
            System.out.println("scoreDoc size: " + hits.scoreDocs.length);
            for (int i = 0; i < hits.totalHits; i++) {
                int id = hits.scoreDocs[i].doc;
                Document docHit = idxSearcher.doc(id);
                String text = docHit.get("content");
                TokenStream tokenStream = TokenSources.getAnyTokenStream(idxReader, id, "content", analyzer);
                TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, text, true, 1);
                for (int j = 0; j < frag.length; j++) {
                    if ((frag[j] != null) && (frag[j].getScore() > 0)) {
                    	System.out.println("-----------------------------------------");
                        System.out.println((frag[j].toString()));
                        return frag[j];
                    }
                }
                
               
 
              /*  System.out.println("start highlight the title");
                // Term vector
                text = docHit.get("title");
                tokenStream = TokenSources.getAnyTokenStream(
                        idxSearcher.getIndexReader(), hits.scoreDocs[i].doc,
                        "title", analyzer);
                frag = highlighter.getBestTextFragments(tokenStream, text,
                		true, 4);
                for (int j = 0; j < frag.length; j++) {
                    if ((frag[j] != null) && (frag[j].getScore() > 0)) {
                        System.out.println((frag[j].toString()));
                    }
                }*/
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
}