package com.java.jobsearchengine.nlp;

import com.java.jobsearchengine.job.Job;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NlpService {

    private static final String[] keyWords = {"experience", "years"};

    @Bean
    public static boolean scoreKeyWords(){
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String jobDescription = "Software developer with 1 years of experience in Java development and proven knowledge of computer science";

        Span[] sentences = tokenizer.tokenizePos(jobDescription);

        for (Span sentence : sentences){
            String sentenceText = sentence.getCoveredText(jobDescription).toString();

            // Check if the sentence contains relevant keywords
            if (containsKeyWords(sentenceText)) {
                // Process the sentence to determine if it refers to 0-2 years of experience
                if (containsYoe(sentenceText)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean containsKeyWords (String sentence){
        for (String word : keyWords){
            if (sentence.contains(word)) return true;
        }
        return false;
    }

    private static boolean containsYoe (String sentence){
        return sentence.matches(".*\\b(0|1|2)\\b.*"); //regex
    }
}
