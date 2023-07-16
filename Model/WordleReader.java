package Model;


import java.util.Iterator;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class WordleReader implements Iterator<String> {

    private BufferedReader reader;

    private List<String> wordList;
    private String line;
    private String word;

    public WordleReader(BufferedReader reader) {
        this.wordList = new LinkedList<>();
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
        try {
            this.line = reader.readLine();
            this.word = this.line.split(",")[1];
        } catch (IOException e) { // test this?
            this.line = null;
        }
    }

    public WordleReader(String filePath) {

        this(fileToReader(filePath));

    }
    public static BufferedReader fileToReader(String filePath) throws IllegalArgumentException {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean hasNext() {
        return line != null;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String toReturn = "";
        try {
            if (this.line != null) {
                toReturn = this.line;
                this.line = reader.readLine();
            }
        } catch (IOException e) { // test?
            this.line = null;
        }
        return toReturn;
    }

    public static List<String> createWordList() {

        List<String> wordlist = new LinkedList<>();
        BufferedReader reader =
                fileToReader("files/" +
                        "5_letter_words.csv");
        WordleReader wr = new WordleReader(reader);
        while (wr.hasNext()) {
            String line = wr.next();
            wordlist.add(line.split(",")[1]);
        }
        return wordlist;
    }
}

