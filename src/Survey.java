import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by lee on 9/8/16.
 */
public class Survey {

    public static UserSubmission user = new UserSubmission();
    public static Scanner scanner = new Scanner(System.in);

    public static final String question1 = "1. Describe your initial experience with using our app.";
    public static final String question2 = "2. What errors (if any) did you encounter with login?";
    public static final String question3 = "3. Describe you level of satisfaction with the functionality and ease of use.";
    public static final String question4 = "4. Did you ever encounter a dead end such as not knowing what to enter, but having no escape from a prompt?";
    public static final String question5 = "5. Would you recommend our app to other people? ";

    public static void main (String[] args) {
        try {
            user = loadUserData();
            System.out.println("Welcome back! Here are your previous answers.\n");
        } catch (FileNotFoundException e) {
            System.out.println("Welcome, new user!\n");
        }

        printQuestions();

        System.out.println("Press [Enter] to being the survey.");
        scanner.nextLine();

        System.out.println(question1);
        user.setAnswer1(scanner.nextLine());

        System.out.println(question2);
        user.setAnswer2(scanner.nextLine());

        System.out.println(question3);
        user.setAnswer3(scanner.nextLine());

        System.out.println(question4);
        user.setAnswer4(scanner.nextLine());

        System.out.println(question5);
        user.setAnswer5(scanner.nextLine());

        try {
            saveUserData();
            System.out.println("Thank you for your input.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveUserData() throws IOException {
        JsonSerializer s = new JsonSerializer();
        String json = s.serialize(user);

        File f = new File("userdata.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static UserSubmission loadUserData() throws FileNotFoundException {
        File f = new File("userdata.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();
        s.close();

        JsonParser p = new JsonParser();

        return p.parse(contents, UserSubmission.class);
    }

    public static void printQuestions() {
        System.out.println(question1);
        if (user.getAnswer1() != null)
            System.out.printf("%s\n", user.getAnswer1());
        else {
            System.out.println();
        }

        System.out.println(question2);
        if (user.getAnswer2() != null)
            System.out.printf("%s\n", user.getAnswer2());
        else {
            System.out.println();
        }

        System.out.println(question3);
        if (user.getAnswer3() != null)
            System.out.printf("%s\n", user.getAnswer3());
        else {
            System.out.println();
        }

        System.out.println(question4);
        if (user.getAnswer4() != null)
            System.out.printf("%s\n", user.getAnswer4());
        else {
            System.out.println();
        }

        System.out.println(question5);
        if (user.getAnswer5() != null)
            System.out.printf("%s\n", user.getAnswer5());
        else {
            System.out.println();
        }
    }
}
