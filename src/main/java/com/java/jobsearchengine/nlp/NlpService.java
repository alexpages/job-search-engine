package com.java.jobsearchengine.nlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.springframework.stereotype.Service;
import java.io.InputStream;


@Service
public class NlpService {

    private static final String[] keyWords = {"experience", "years"};

    public static boolean validateJob(String jobDescription) {
        String[] sentences = detectSentence(jobDescription.replaceAll("\n", "."));
        for (String sentence : sentences){
            if (containsKeyWords(sentence)) {   // Check if the sentence contains relevant keywords
                if (containsYoe(sentence)) {    // Process the sentence to determine if it refers to 0-2 years of experience
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] detectSentence(String paragraph) {
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

    public static boolean containsKeyWords(String sentence){
        for (String word : keyWords){
            if (sentence.contains(word)) return true;
        }
        return false;
    }

    public static boolean containsYoe(String sentence){
        sentence = sentence.toLowerCase();
        return sentence.matches(".*\\b(0|1|2)\\b.*"); //regex
    }
}
