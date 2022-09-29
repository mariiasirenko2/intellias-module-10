import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static List<String> readFile(File file) {
        List<String> words = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new java.io.FileReader(file))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                words.add(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return words;
    }

    public static void writeToFile(String text, String fileName) {

        try (FileWriter writer = new FileWriter(new File(fileName))) {

            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> splitTextToWords(List<String> text, String splitter) {
        return text.stream()
                .flatMap((p) -> Arrays.stream(p.split(splitter)))
                .toList();
    }
}
