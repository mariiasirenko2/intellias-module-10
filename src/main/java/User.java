import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static class UserReader {
        public static List<User> readUsersFromFile(String filename) {

            List<String> names = Utils.readFile(new File(filename));

            return names.stream()
                    .skip(1)
                    .map(s -> new User(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])))
                    .toList();
        }
    }

    static class UserToJsonConverter {
        public static String castUserAsJson(List<User> users) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return users.stream().map(gson::toJson).collect(Collectors.joining(",\n", "[", "]"));
        }

        public static String castUserAsJson(User user) {
            return castUserAsJson(Collections.singletonList(user));
        }
    }


}
