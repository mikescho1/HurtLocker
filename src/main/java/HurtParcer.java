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
        String regexAllMilks = "(milk);price:[13].23";
        String regexMilk323 = "(milk);price:3.23";
        String regexMilk123 = "(milk);price:1.23";

        Pattern patternallMilks = Pattern.compile(regexAllMilks, Pattern.CASE_INSENSITIVE);
        Pattern pattern323 = Pattern.compile(regexMilk323, Pattern.CASE_INSENSITIVE);
        Pattern pattern123 = Pattern.compile(regexMilk123, Pattern.CASE_INSENSITIVE);

        Matcher mAllMilk = patternallMilks.matcher(nameChanged);
        Matcher m323 = pattern323.matcher(nameChanged);
        Matcher m123 = pattern123.matcher(nameChanged);

        int countAllMilk = 0;
        int count323 = 0;
        int count123 = 0;

        String changeFirstMilk323 = "";
        String milkCaseAndOccurrenceChanged = "";

        while (mAllMilk.find()) {
            countAllMilk++;
        }
        while (m123.find()) {
            count123++;
        }
        while (m323.find()) {
            count323++;
        }

        changeFirstMilk323 = m323.replaceFirst("Milk\t\tseen: " + countAllMilk + " times\n" +
                "=============\t\t=============\n" +
                "Price:\t 3.23\t\tseen: " + count323 + " times\n" +
                "-------------\t\t-------------\n" +
                "Price:   1.23\t\tseen: " + count123 + " times\n" +
                "\n");    //formats fist milk occurrence.
        regexMilk323 = "milk;price:3.23;";
        pattern323 = Pattern.compile(regexMilk323, Pattern.CASE_INSENSITIVE);

        m323 = pattern323.matcher((changeFirstMilk323));
        milkCaseAndOccurrenceChanged = m323.replaceAll("");


        return this.milkCaseAndOccurrenceChanged = milkCaseAndOccurrenceChanged;
    }

    public String countAndRemoveDuplicateMilkPrices(String milkCaseAndOccurrenceChanged) {
        String regex = " .?((?i)(price)).?(3.23)+";
        String regex1 = " .?((?i)(price)).?(1.23)+";
        String regex2 = " .?((?i)(price)).?(1.23)+";

        Pattern pattern = Pattern.compile(regex);
        Pattern lowPricePattern = Pattern.compile(regex1);
        Pattern noPricePattern = Pattern.compile(regex2);

        Matcher m = pattern.matcher(milkCaseAndOccurrenceChanged);
        Matcher m1 = lowPricePattern.matcher(milkCaseAndOccurrenceChanged);
        Matcher m2 = noPricePattern.matcher(milkCaseAndOccurrenceChanged);

        String removeDuplicatetMilkPrices = "";
        String milkPricesChanged = "";
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        while (m.find() && m1.find() && m2.find()) {
            if (m.find()) {
                count++;
            }
            if (m1.find()) {
                count1++;
            }
            if (m2.find()) {
                count2++;
            }
        }
        removeDuplicatetMilkPrices = m.replaceAll("");
        regex = ";price:3.23";
        pattern = pattern.compile(regex);
        m = pattern.matcher(removeDuplicatetMilkPrices);
        milkPricesChanged = m.replaceAll("Price: \t 3.23\t\tseen: " + count + " times\n-------------\t\t-------------\n\n");

        return this.milkPricesChanged = milkPricesChanged;
    }
}


//    public Integer countMilkOccurrences(String milk) {
//        String regex = "(?i)(milk)";
//        Pattern pattern = Pattern.compile(regex);
//    }






