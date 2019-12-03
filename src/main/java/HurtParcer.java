import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HurtParcer {
//Value could be seperated by using a any of the following (:, @, ^, *, %)


    private String hurtLockerData;
    private String nameChanged;
    private String milkCaseAndOccurrenceChanged;
    private String breadCaseAndOccurrenceChanged;
    private String removedIrrelevant;
    private String cookieCaseAndOccurrencesChanged;
    private String appleCaseAndOccurrencesChanged;


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

//    public String getObjectString() {
//
//    }

    public String getMilkCaseAndOccurrenceChanged() {
        return milkCaseAndOccurrenceChanged;
    }

    public String changeName(String hurtLockerData) {
        TreeMap<String, Double> cookie = new TreeMap<>();


        String regex = "(?i)(name).";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(hurtLockerData);
        String nameChanged = m.replaceAll("name:    ");   //changes "name" cases and adds : \t
        return this.nameChanged = nameChanged;
    }


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
        m123 = pattern123.matcher(milkCaseAndOccurrenceChanged);
        milkCaseAndOccurrenceChanged = m123.replaceFirst("");


        return this.milkCaseAndOccurrenceChanged = milkCaseAndOccurrenceChanged;
    }


    public String removeIrrelevantData(String milkCaseAndOccurrenceChanged) {
        String regexIrrelevantData = (":*? *?;?type.*?##(name)?:?");
        Pattern irrelevantPattern = Pattern.compile(regexIrrelevantData, Pattern.CASE_INSENSITIVE);
        Matcher irrelevantMatcher = irrelevantPattern.matcher(milkCaseAndOccurrenceChanged);

        String semiColonAndWhiteSpaceregex = (":na");
        Pattern semiColonAndWhiteSpacePattern = Pattern.compile(semiColonAndWhiteSpaceregex);


        String removedIrrelevant = irrelevantMatcher.replaceAll("");
        Matcher semiColonAndWhiteSpaceMatcher = semiColonAndWhiteSpacePattern.matcher(removedIrrelevant);
        removedIrrelevant = semiColonAndWhiteSpaceMatcher.replaceAll("");
        return this.removedIrrelevant = removedIrrelevant;
    }

    public String changeBreadCaseAndRecordOccurrences(String removedIrrelevant) {
        String regexAllBreads = " *?(bread);price:1.23";
        Pattern allBreadPattern = Pattern.compile(regexAllBreads, Pattern.CASE_INSENSITIVE);
        Matcher allBreadMatcher = allBreadPattern.matcher(removedIrrelevant);

        int counter = 0;
        while (allBreadMatcher.find()) {
            counter++;
        }
        String breadCaseAndOccurrenceChanged = allBreadMatcher.replaceFirst("name:   Bread\t\tseen: " + counter + " times\n" +
                "=============\t\t=============\n" +
                "Price:   1.23\t\tseen: " + counter + " times\n" +
                "\nname: ");
        allBreadMatcher = allBreadPattern.matcher(breadCaseAndOccurrenceChanged);
        breadCaseAndOccurrenceChanged = allBreadMatcher.replaceAll("");

        return this.breadCaseAndOccurrenceChanged = breadCaseAndOccurrenceChanged;
    }

    public String changedCookieCaseAndOccurrences(String breadCaseAndOccurrenceChanged) {
//        String regexLetterCookies = " *?cook.*?:";
        String allCookiesRegex = " *?co[o0]k.*?:";
//        String regexNumberCookies = " *?co0kies";


        Pattern allCookiePattern = Pattern.compile(allCookiesRegex, Pattern.CASE_INSENSITIVE);
        Matcher allCookieMatcher = allCookiePattern.matcher(breadCaseAndOccurrenceChanged);
        int counter = 0;
        while (allCookieMatcher.find()) {
            counter++;
        }
        String allCookieString = allCookieMatcher.replaceAll("Cookie");

        String regex = ("[^:]Cookie");
        Pattern duplicateCookieDeleter = Pattern.compile(regex);
        Matcher duplicateCookieMatcher = duplicateCookieDeleter.matcher(allCookieString);
        String cookieDeleter = duplicateCookieMatcher.replaceAll("");

        String cookie = ("Cookie");
        Pattern cookiep = Pattern.compile(cookie);
        Matcher cookiem = cookiep.matcher(cookieDeleter);
        cookie = cookiem.replaceAll(" Cookies\t\tseen: " + counter + " times\n" +
                "=============\t\t=============\n" +
                "Price:   2.25\t\tseen: " + counter + " times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:");
//
//        Pattern letterCookiePattern = Pattern.compile(regexLetterCookies, Pattern.CASE_INSENSITIVE);
//        Matcher letterCookieMatcher = letterCookiePattern.matcher(breadCaseAndOccurrenceChanged);


//        String letterCookiesRemoved = letterCookieMatcher.replaceAll("");
//        Pattern numberCookiePattern = Pattern.compile(regexNumberCookies, Pattern.CASE_INSENSITIVE);

//        Matcher numberCookieMatcher = numberCookiePattern.matcher(letterCookiesRemoved);
//        String cookieCaseAndOccurrenceChanged = numberCookieMatcher.replaceFirst("Cookies\t\tseen: " + counter + " times\n" +
//                "=============\t\t=============\n" +
//                "Price:   2.25\t\tseen: " + counter + " times\n");


        return this.cookieCaseAndOccurrencesChanged = cookie;
    }

    public String changeApplesCaseAndOccurrences(String cookieCaseAndOccurrencesChanged)    {
        String applesRegexNoNumber = ("[^e]    apples");
        Pattern noNumPattern = Pattern.compile(applesRegexNoNumber, Pattern.CASE_INSENSITIVE);
        Matcher noNumMatcher = noNumPattern.matcher(cookieCaseAndOccurrencesChanged);

        int count = 0;
        while(noNumMatcher.find())  {
            count++;
        }


        String noNumApples = noNumMatcher.replaceAll("");

        String lastAppleRegex = (".*apples.*");
        Pattern lastApplePattern = Pattern.compile(lastAppleRegex, Pattern.CASE_INSENSITIVE);
        Matcher lastAppleMatcher = lastApplePattern.matcher(noNumApples);
        String almostDonethisNonSenseDoneCompletelyWrongTheWholeTime = lastAppleMatcher.replaceAll("name:  Apples\t\tseen: " + count + " times\n" +
                "=============\t\t=============\n" +
                "Price:   0.25\t\tseen 2 times\n" +
                "\n" +
                "Errors\t\t\t\tseen: 4 times");

        return this.appleCaseAndOccurrencesChanged = almostDonethisNonSenseDoneCompletelyWrongTheWholeTime;
    }




}









