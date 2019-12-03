import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

        HurtParcer hurtParcer = new HurtParcer();
        String hurtLockerData = hurtParcer.getHurtLockerData();






        String nameChanged = hurtParcer.changeName(hurtLockerData);
        String milkCaseAndOccurrenceChanged = hurtParcer.changeMilkCaseAndRecordOccurrences(nameChanged);
        String removedIrrelevant = hurtParcer.removeIrrelevantData(milkCaseAndOccurrenceChanged);
        String breadCaseAndOccurrenceChanged = hurtParcer.changeBreadCaseAndRecordOccurrences(removedIrrelevant);
        String cookieCaseAndOccurrencesChanged = hurtParcer.changedCookieCaseAndOccurrences(breadCaseAndOccurrenceChanged);

        System.out.println(nameChanged);
        System.out.println(milkCaseAndOccurrenceChanged);
        System.out.println(removedIrrelevant);
        System.out.println(breadCaseAndOccurrenceChanged);
        System.out.println(cookieCaseAndOccurrencesChanged);

    }
}
