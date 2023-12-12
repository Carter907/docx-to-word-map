package org.example;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("please enter a path to the .docx file");
            System.exit(0);
        }

        try (FileInputStream inputFile = new FileInputStream(args[0])) {
            XWPFDocument document = new XWPFDocument(inputFile);
            List<String> words = new ArrayList<>();
            // Iterate through paragraphs
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                words.add(paragraph.getText());
            }
            HashMap<String, Integer> wordCounts = new HashMap<>();
            words.stream()
                    .flatMap((str) -> List.of(str.split(" ")).stream())
                    .forEach((str) -> {
                        wordCounts.put(str, wordCounts.getOrDefault(str, 0) + 1);

                    });

            if (args.length == 1)
                for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                    System.out.println(entry);
                }
            else {
                try (FileOutputStream fileOut = new FileOutputStream(args[1])) {
                    fileOut.write(wordCounts.toString().getBytes(StandardCharsets.UTF_8));
                    System.out.println("completed writing to file");
                } catch (FileNotFoundException e) {
                    System.err.println("could not find file: " + args[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}