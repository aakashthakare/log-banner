package in.at;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

public class BannerGeneratorTest {

    @Test
    public void testHello() {
        assertEquals(banner("HELLO-1.txt"), BannerGenerator.generate("HELLO-1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowercaseLetter() {
        BannerGenerator.generate("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedSymbol() {
        BannerGenerator.generate("$");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testExceedWordLimit() {
        BannerGenerator.generate("ABCDEFGHIJKLMNOPQRST");
    }

    private String banner(String fileName) {
        try {
            URL fileUrl = BannerGenerator.class.getClassLoader().getResource(fileName);
            File hello1 = new File(fileUrl.toURI());
            return new String(Files.readAllBytes(hello1.toPath()));
        } catch (URISyntaxException | IOException e) {
            throw new IllegalArgumentException("Invalid file name.");
        }
    }

}
