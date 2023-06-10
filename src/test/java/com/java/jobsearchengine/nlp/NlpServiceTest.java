package com.java.jobsearchengine.nlp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class NlpServiceTest {

    @Autowired
    private NlpService underTest;

    @Test
    void scoreKeyWords() {
        //given
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
        //when
        boolean result = underTest.validateJob(jobDescription);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void containsKeyWords(){
        //given
        String sentence1 = "Working knowledge of : Java, J2EE, Javascript, XML, HTML, CSS, Tomcat, Ant, Eclipse";
        String sentence2 = "At least 1 year of relevant experience";
        //when
        boolean result1 = underTest.containsKeyWords(sentence1);
        boolean result2 = underTest.containsKeyWords(sentence2);
        //then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
    }

    @Test
    void detectSentence(){
        //given
        String paragraph = "Working knowledge of : Java, J2EE, Javascript. At least 1 year of relevant experience.";
        //when
        String[] result = underTest.detectSentence(paragraph);
        String[] toCompare = {"Working knowledge of : Java, J2EE, Javascript.","At least 1 year of relevant experience."};
        //then
        assertThat(result).isEqualTo(toCompare);
    }

    @Test
    void containsYoe() {
        //given
        String sentence1 = "At least 1 year of relevant experience.";
        String sentence2 = "For this role it may be required and experience of +3 years of relevant experience.";
        //when
        boolean result1 = underTest.containsYoe(sentence1);
        boolean result2 = underTest.containsYoe(sentence2);
        //then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}
