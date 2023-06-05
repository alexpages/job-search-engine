package com.java.jobsearchengine.nlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.io.InputStream;


@Service
public class NlpService {

    private static final String[] keyWords = {"experience", "years"};

    @Bean
    public static boolean scoreKeyWords() {
        String jobDescription = "Position Requirements\n" +
                "\n" +
                "MS or Equivalent\n" +
                "Working knowledge of : Java, J2EE, Javascript, XML, HTML, CSS, Tomcat, Ant, Eclipse\n" +
                "Database knowledge: PL/SQL, PostgreSQL, Oracle\n" +
                "At least 1 year of relevant experience\n" +
                "Basic knowlage of Retail flows\n" +
                "Other tools: Hibernate, Weld, HQL, RESTful web services, Jasper Reports, HTLM5, React, IndexedDB, Cypress\n" +
                "Product quality focus\n" +
                "Proven ability to meet deadlines\n" +
                "High level of English (Spanish and/or French would be a valuable plus)";
        String[] sentences = detectSentence(jobDescription);
        for (String sentence : sentences){
            if (containsKeyWords(sentence)) {            // Check if the sentence contains relevant keywords
                if (containsYoe(sentence)) {                 // Process the sentence to determine if it refers to 0-2 years of experience
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] detectSentence(String paragraph) {
        String[] sentences = null;
        InputStream is = NlpService.class.getResourceAsStream("/models/en-sent.bin"); //Model for sentence detection
        try {
            SentenceModel model = new SentenceModel(is);
            SentenceDetectorME sdetector = new SentenceDetectorME(model);
            sentences = sdetector.sentDetect(paragraph);
        } catch (Exception e) {
            System.out.println("Problem with detectSentence function");
        }
        return sentences;
    }

    private static boolean containsKeyWords(String sentence){
        for (String word : keyWords){
            if (sentence.contains(word)) return true;
        }
        return false;
    }

    private static boolean containsYoe(String sentence){
        sentence = sentence.toLowerCase();
        return sentence.matches(".*\\b(0|1|2)\\b.*"); //regex
    }
}
