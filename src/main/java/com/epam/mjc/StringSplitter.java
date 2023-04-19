package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List <String> result = new ArrayList<>();
        for (String delimiter : delimiters) {
            for (int i = 0; i < source.length(); i++) {
                if (source.charAt(i) == delimiter.charAt(0)) {
                    source = source.replace(source.charAt(i), ' ');
                }
            }
        }
        String[] resultStrArray = source.split("\\s+");
        for (String s : resultStrArray) {
            if (!Objects.equals(s, "")) {
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StringSplitter splitter = new StringSplitter();
        System.out.println(splitter.splitByDelimiters("qwecb112kbmgb33mfv555", List.of("1", "3", "5")));
    }
}


