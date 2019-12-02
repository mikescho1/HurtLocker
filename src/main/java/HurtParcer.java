import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HurtParcer {
//Value could be seperated by using a any of the following (:, @, ^, *, %)

    private String hurtLockerData;
    private String



    public HurtParcer() {
        this.hurtLockerData = loadFile();
    }

    private String loadFile() {
        File file = new File("/Users/mike/dev/week8/labs/HurtLocker/src/main/resources/RawData.txt");
        StringBuilder result = new StringBuilder("");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String getHurtLockerData() {
        return hurtLockerData;
    }

    public String changeName(String stringToParse)  {
        String regex = "(?i)(name).";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(stringToParse);
        String newName = m.replaceAll("name:");
        return newName;
    }

    public String changeItem(StringToParse) {
        String regex = "(?i)(name:)(".*)
    }


}
