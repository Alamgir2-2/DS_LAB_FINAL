package Ques2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapReduce {
    private String message;
    private List<Node> wordList;

    public MapReduce(String message) {
        this.message = message;
        wordList = new ArrayList<>();
        mapping();

    }

    public String[] splitting() {
        return message.split(" ");
    }

    public void mapping() {
        for (String word : splitting()) {
            wordList.add(new Node(word, 0));
        }
    }

    public void startWithVowel() {
        for (Node node : wordList) {
            if (node.getWord().startsWith("A")
                    || node.getWord().startsWith("E")
                    || node.getWord().startsWith("I")
                    || node.getWord().startsWith("O")
                    || node.getWord().startsWith("U")) {
                node.setCount(1);
            }
        }
    }

    public void isArticle() {
        for (Node node : wordList) {
            if (node.getWord().equals("A")
                    || node.getWord().equals("AN")
                    || node.getWord().equals("THE")) {
                node.setCount(1);
            }
        }
    }

    public void sort() {
        Collections.sort(wordList);
    }

    public String showOutput() {
        String output = "";
        for (Node node : wordList) {
            output += node + "\n";
        }
        return output;
    }
}

class Node implements Comparable{
    private String word;
    private int count;

    public Node(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "word=" + word + '\'' + ", Vowel=" + count;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Node) {
            return ((Node) o).getCount() - getCount();
        }
        return 0;
    }
}
