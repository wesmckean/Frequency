/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandbox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author wmckean
 */
public class Main { 
    private static final class Frequency  implements Comparable {
        private final String word;
        private Integer count;
        
        public Frequency(String word, Integer count) {
            this.word = word;
            this.count = count;
        }
        
        public String getWord() {
            return word;
        }
        
        public Integer getCount() {
            return count;
        }
        
        public void increment() {
            count = count + 1;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(count).append(' ').append(word);
            return sb.toString();
        }

        @Override
        public int compareTo(Object o) {
            int result = 0;
            
            if(o instanceof Frequency f) {
                result = f.count - count;
            }
            
            return result;
        }
    }
    
    public static void main(String args[]) throws Exception {
        String data = getData(args);
        
        if("".equals(data) == false) {
            Map<String, Frequency> frequencyMap =  buildFrequency(data);
            dump(frequencyMap);
        }
        
     }
    
    private static String getData(String args[]) throws IOException {
        String result = "";
        
        if(args.length == 1) {
            result = args[0];
        }
        else if(args.length == 2 && "-f".equals(args[0])) {
            File f = new File(args[1]);
            List<String> list = Files.readAllLines(f.toPath());
            result = String.join("\n", list);
        }
        
        return result;
    }
    
    private static Map<String, Frequency> buildFrequency(String data) {
       BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
        bi.setText(data);
        
        Map<String, Frequency> result = new HashMap<>();
        
        int lastIndex = bi.first();
        while(lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = bi.next();
            
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(data.charAt(firstIndex))) {
                String word = data.substring(firstIndex, lastIndex);
                Frequency f = result.computeIfAbsent(word, (w)->new Frequency(w,0));
                f.increment();
            }            
        }
        
        return result;
    }
    
    private static void dump(Map<String, Frequency> map) {
         map.values().stream().sorted().forEach(System.out::println);
    }
}
