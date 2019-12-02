import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HurtParcer {
//Value could be seperated by using a any of the following (:, @, ^, *, %)


    private String hurtLockerData;
    private String nameChanged;
    private String milkCaseAndOccurrenceChanged;
    private String milkPricesChanged;


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

    public String getNameChanged() {
        return nameChanged;
    }

    public String getMilkCaseAndOccurrenceChanged() {
        return milkCaseAndOccurrenceChanged;
    }

    public String changeName(String hurtLockerData) {

        String regex = "(?i)(name).";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(hurtLockerData);
        String nameChanged = m.replaceAll("name:    ");   //changes "name" cases and adds : \t
        return this.nameChanged = nameChanged;
    }

    //    public String changeItem(String nameChanged) {
//        String regex = "(?i)(name:)(.*);";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher m = pattern.matcher(nameChanged);
//        String itemChanged =
//
//    }
    public String changeMilkCaseAndRecordOccurrences(String nameChanged) {

        String regex = "(?i)(milk)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(nameChanged);
        int count = 0;
        String changeFirstMilkCase = "";
        String milkCaseAndOccurrenceChanged = "";

        while (m.find()) {
            count++;
        }
        changeFirstMilkCase = m.replaceFirst("Milk\t\tseen: " + count + " times\n=============\t\t=============\n");    //formats fist milk occurrence.
        regex = "(?i)(milk);";
        pattern = pattern.compile(regex);
        m = pattern.matcher((changeFirstMilkCase));
        milkCaseAndOccurrenceChanged = m.replaceAll("");    //removes duplicate milk occurrences.

        return this.milkCaseAndOccurrenceChanged = milkCaseAndOccurrenceChanged;
    }

    public String countAndRemoveDuplicateMilkPrices(String milkCaseAndOccurrenceChanged) {
        String regex = " .?((?i)(price)).?(3.23)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(milkCaseAndOccurrenceChanged);
        String removeDuplicatetMilkPrices = "";
        String milkPricesChanged = "";
        int count = 0;
        while (m.find()) {
            count++;
        }
        removeDuplicatetMilkPrices = m.replaceAll("");
        regex = ";price:3.23";
        pattern = pattern.compile(regex);
        m = pattern.matcher(removeDuplicatetMilkPrices);
        milkPricesChanged = m.replaceAll("Price: \t 3.23\t\tseen: " + count + " times\n-------------\t\t-------------\n\n");

        return this.milkPricesChanged = milkPricesChanged;
    }


//    public Integer countMilkOccurrences(String milk) {
//        String regex = "(?i)(milk)";
//        Pattern pattern = Pattern.compile(regex);
//    }


}



