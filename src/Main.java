import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String str = "";
        BufferedReader br1 = new BufferedReader( new InputStreamReader( System.in ));
        System.out.println( "Enter file name; 'stop' to quit." );
        try {
        str = br1.readLine();
        File file = new File(str);
        } catch (IOException exc) {
        System.out.println( "I/O error: " + exc );
        }
        String s;
        File file = new File(str);

        Map<String, Integer> words = new HashMap<String, Integer>();
        ArrayList<String> array = new ArrayList<>();
        Map<String, Integer> lineCount = new HashMap<String, Integer>();
        Map<String, Set<Integer>> myWord = new HashMap<String, Set<Integer>>();
        try (Scanner input = new Scanner(file)) {
            int l2 = 0;
            while (input.hasNextLine()) {
                Set<Integer> mySet = new HashSet<Integer>();
                Scanner line = new Scanner(input.nextLine());
                l2++;
                while (line.hasNext()) {
                    //mySet.clear();
                    int count = 1;
                    String word = line.next();
                    String word2 = word.replaceAll("[^a-zA-Z]", "");
                    word2 = word2.toLowerCase();
                    if (word2 != null || word2 != " ") {
                        //array.add(word2);
                        if (myWord.containsKey(word2)) {
                            //System.out.println("in " + word2 + " " + l2);
                            //lineCount.put(word2, l2);
                            mySet.add(l2);
                            myWord.put(word2, mySet);
                        } else if (!myWord.containsKey(word2)) {
                            //System.out.println("out " + word2 + " " + l2);
                            //lineCount.put(word2, l2);
                            mySet.add(l2);
                            myWord.put(word2, mySet);
                        }
                        //words.put(word2, l2);
                    }
                    //System.out.print(array);
                    }
                }
            //System.out.println(myWord);
        } catch (FileNotFoundException e) {
            System.out.println("File not found Error: " + e);
        }

        try( FileWriter fw = new FileWriter( "out.txt" )){
            for (Map.Entry<String, Set<Integer>> word : myWord.entrySet()) {
            System.out.print(word.getKey());
            System.out.print(": ");
            System.out.println(word.getValue());
            fw.write(word.getKey());
            fw.write(": ");
            fw.write(word.getValue().toString());
            fw.write("\n");
        }
        } catch (IOException e) {
            System.out.println("File not found Error: " + e);
        }
}
}