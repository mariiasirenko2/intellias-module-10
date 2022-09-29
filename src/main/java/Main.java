import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Task1
        List<String> phones = Utils.readFile(new File("Task1_Phones.txt"));
        System.out.println("Task 1. Starter file: " + phones);

        List<String> filteredPhones = getRealPhoneNumbers(phones);
        System.out.println("Filter phone numbers: " + filteredPhones);


        //Task2
        List<String> names = Utils.readFile(new File("Task2_Names.txt"));
        System.out.println("Task 2. Starter file: " + names);

        List<User> users = User.UserReader.readUsersFromFile("Task2_Names.txt");
        String json = User.UserToJsonConverter.castUserAsJson(users);

        Utils.writeToFile(json, "Users.json");


        //Task 3
        List<String> text = Utils.readFile(new File("Task3_Words.txt"));
        System.out.println("Task 3. Starter file: " + text);

        List<Map.Entry<String, Long>> sortedWordsCounter = getWordsFrequency(text);

        System.out.println("Word frequency: " + sortedWordsCounter);

    }

    public static List<String> getRealPhoneNumbers(List<String> phoneNumbers) {
        Pattern pattern = Pattern.compile("(\\([0-9]{3}\\) [0-9]{3}-[0-9]{4})|([0-9]{3}-[0-9]{3}-[0-9]{4})");
        return phoneNumbers.stream().filter(pattern.asPredicate()).toList();
    }

    public static List<Map.Entry<String, Long>> getWordsFrequency(List<String> text) {


        Map<String, Long> wordsCounter = Utils.splitTextToWords(text, " ").stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map.Entry<String, Long>> sortedWordsCounter = new ArrayList<>(wordsCounter.entrySet());
        sortedWordsCounter.sort((o1, o2) -> Math.toIntExact(o2.getValue() - o1.getValue()));

        return sortedWordsCounter;

    }


}
