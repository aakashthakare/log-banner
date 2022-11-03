package in.at;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BannerGenerator {

    private static final int BIG_CHAR_LINES = 5;

    private static final int NON_BIG_CHAR_LINES = 2;

    private static final int CHAR_WIDTH = 9;

    public static final String WORD_FORMAT = String.format("%%-%ds", CHAR_WIDTH);

    public static String generate(String word) {
        Map<Character, List<String>> charToBigCharMap = bigCharacters();
        List<List<String>> bigChars = word.chars().filter(BannerGenerator::validateChar)
                                                    .mapToObj(codePoint -> charToBigCharMap.get((char)codePoint))
                                                    .collect(Collectors.toList());

        return createBanner(word.length(), bigChars);
    }

    private static String createBanner(int wordLength, List<List<String>> bigChars) {
        StringBuilder wordLine = new StringBuilder();

        String border = border(wordLength);
        wordLine.append(border);
        int lineNo = 0;
        while(lineNo < BIG_CHAR_LINES) {
            for(List<String> bigChar : bigChars) {
                wordLine.append(String.format(WORD_FORMAT, bigChar.get(lineNo)));
            }
            wordLine.append(System.lineSeparator());
            lineNo++;
        }
        wordLine.append(border);
        return wordLine.toString();
    }

    private static boolean validateChar(int c) {
        if(Character.isUpperCase(c) || Character.isDigit(c) || c == '-') {
            return true;
        }
        throw new IllegalArgumentException("Only uppercase letters, digits and hyphen characters are supported.");
    }

    private static String border(int wordLength) {
        StringBuilder border = new StringBuilder();
        IntStream.range(0, wordLength * CHAR_WIDTH).forEach(i -> border.append("="));
        border.append(System.lineSeparator());
        return border.toString();
    }

    private static Map<Character, List<String>> bigCharacters() {
        List<String> bigChars = null;
        try {
            URL fileUrl = BannerGenerator.class.getClassLoader().getResource("alphabets.txt");
            File alphabets = new File(fileUrl.toURI());
            bigChars = Files.readAllLines(alphabets.toPath());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        Map<Character, List<String>> charToBigCharMap = new HashMap<>();
        int lineNo = 0;
        while(lineNo < bigChars.size()) {
            char c = bigChars.get(lineNo).charAt(0);
            int start = lineNo + 1;
            charToBigCharMap.put(c, bigChars.subList(start, start + BIG_CHAR_LINES));
            lineNo += (BIG_CHAR_LINES + NON_BIG_CHAR_LINES);
        }
        return charToBigCharMap;
    }
}
