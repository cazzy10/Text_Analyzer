
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordSearch {

    private static final String FILE_NAME = "C:\\Users\\Marlene\\Documents\\valencia\\poem.txt";

    private Map<String, Integer> wordMaze;

    public WordSearch() {
        wordMaze = new HashMap<>();
    }

    public void readFile(String fileName) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;

            while ((line= reader.readLine()) != null) {
                line = line.toLowerCase();
                line = line.replaceAll("[^\\sa-zA-Z]", "");
                line = line.replaceAll("\\s+", " ").trim();

                if (!line.isEmpty()) {
                    String[] s = line.split(" ");
                    for (String token : s) {
                        if (wordMaze.containsKey(token)) {
                            Integer count = wordMaze.get(token);
                            wordMaze.put(token, count + 1);
                        } else {
                            wordMaze.put(token, 1);
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFrequency() {

        List<Map.Entry<String, Integer>> linkedMap = new LinkedList<>(wordMaze.entrySet());

        Collections.sort(linkedMap, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -1*o1.getValue().compareTo(o2.getValue());
            }
        });

        int num = 1;
        System.out.printf("%-5s %-10s %-10s\n", "No.", "Word", "Frequency");
        System.out.printf("%-30s\n", "----------------------------");
        for (Map.Entry<String, Integer> wordFrequency : linkedMap) {
            System.out.printf("%-5d %-15s %-5d\n", num, wordFrequency.getKey(), wordFrequency.getValue());
            num++;
        }
    }

    public static void main(String[] args) {

        WordSearch wordLocation = new WordSearch();
        wordLocation.readFile(FILE_NAME);
        wordLocation.printFrequency();

    }

}


