import com.sun.jdi.IncompatibleThreadStateException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        File alphabets = new File("alphabets.txt");
        List<String> alphabetsLines = Files.readAllLines(alphabets.toPath());

        String hello = "SPRINGBOOT";
        List<String> word = new ArrayList<>();
        int lineNo = 0;
        while(lineNo < 5) {
            StringBuilder wordLine = new StringBuilder();
            for(char c : hello.toCharArray()) {
                int start = (c - 'A')  * 6;
                String line = alphabetsLines.get(start + lineNo);
                wordLine.append(String.format("%-9s", line));
            }
            word.add(wordLine.toString());
            lineNo++;
        }

        for (String line : word) {
            System.out.println(line);
        }
    }
}
