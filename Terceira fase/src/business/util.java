package business;

import business.campeonatos.Jogador;

import java.util.*;

public class util {
    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public static String printMapSortedByValue(Map<String, Integer> map) {
        TreeMap<Integer, List<String>> sortedMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!sortedMap.containsKey(entry.getValue())) {
                sortedMap.put(entry.getValue(), new ArrayList<>());
            }
            sortedMap.get(entry.getValue()).add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        int i=1;
        for (Map.Entry<Integer, List<String>> entry : sortedMap.descendingMap().entrySet()) {
            for (String key : entry.getValue()) {
                sb.append(i).append("º ").append(key).append(" : ").append(entry.getKey()).append(" pontos\n");
                i++;
            }
        }
        return sb.toString();
    }

    public static Map<String,Integer> sortMap(Map<String,Integer> map){
        TreeMap<Integer, List<String>> sortedMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!sortedMap.containsKey(entry.getValue())) {
                sortedMap.put(entry.getValue(), new ArrayList<>());
            }
            sortedMap.get(entry.getValue()).add(entry.getKey());
        }
        Map<String,Integer> res = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : sortedMap.entrySet()) {
            for (String key : entry.getValue()){
                res.put(key, entry.getKey());
            }
        }
        return res;
    }
}
