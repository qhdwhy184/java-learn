package algorithm.amz;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://aonecode.com/amazon-online-assessment-questions#cw
 *
 * Most Common Words
 * Amazon is partnering with the linguistics department at a local university to analyze important works of English
 * literature and identify patterns in word usage across different eras. To ensure a cleaner output. the linguistics
 * department has provided a list of commonly used words (e.g., "an", "the". etc.) to exclude from the analysis. In the
 * context of this search, a word is an alphabetic sequence of characters having no whitespace or punctuation.
 *
 * Write an algorithm to find the most frequently used word in the text excluding the commonly used words.
 *
 * Input:
 * The input to the function/method consists of two arguments:
 * literatureText: a string representing the block of text,
 * wordsToExclude: a list of strings representing the commonly used words to be excluded while analyzing the word frequency.
 *
 * Output:
 * Return a list of strings representing the most frequently used word in the text or in case of a tie, all of the most
 * frequently used words in the text..
 *
 * Note:
 * Words that have a different case are counted as the same word. The order of words does not matter in the output list.
 * All words in the ‘wordsToExclude’ list are unique. Punctuation should be treated as white space.
 *
 * Example
 * Input :
 * literature Text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food."
 * wordsToExclude = ["and", "he", "the", "to", "is". "Jack", "Jill"]
 * Output :
 * ["cheese", "s"]
 *
 * Explanation : The word `and" has a maximum of three frequency but this word should be excluded while analyzing the
 * word frequency. The words "Jack’. 'Jill", "s", "to" and "cheese" have the next maximum frequency(two) in the given
 * text but the words "Jack", "to" and "Jill’ should be excluded as these are commonly used words which you are not
 * interested to include.
 */
public class MostCommonWords {
    public String[] solution(String text, String[] exclude) {
        text = text.toLowerCase();

        char[] ctext = text.toCharArray();
        for(int i = 0; i < ctext.length; i++) {
            if(ctext[i] >= 'A' && ctext[i] <= 'Z') {
                ctext[i] += 32;
            } else if (ctext[i] >= 'a' && ctext[i] <= 'z') {
            } else {
                ctext[i] = ' ';
            }
        }
        
        String[] splitText = new String(ctext).split(" ");

//        PriorityQueue<StringCount> countQueue = new PriorityQueue<>();
        HashMap<String, Integer> stringCount = new HashMap<>()   ;
        
        HashSet<String> excludeWords = new HashSet<>();
        for(String e : exclude) {
            excludeWords.add(e.toLowerCase());
        }
        
        for(String s : splitText) {
            if(!excludeWords.contains(s)) {
                Integer c = stringCount.get(s);
                if(c == null) {
                    c = 0;
                }
                stringCount.put(s, c+1);
            }
        }

        List<String> res = new ArrayList<>();
        int maxCount = 0;
        for(Map.Entry<String, Integer> e : stringCount.entrySet()) {
             if(maxCount < e.getValue()) {
                 res.clear();
                 maxCount = e.getValue();
                 res.add(e.getKey());
                 continue;
             } 
             if(maxCount == e.getValue()) {
                 res.add(e.getKey());
             }
        }
        
        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] exclude = {"and", "he", "the", "to", "is", "Jack", "Jill"};
        String text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food.";
        System.out.println(new Gson().toJson(new MostCommonWords().solution(text, exclude)));
    }
}
