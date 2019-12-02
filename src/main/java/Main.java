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
//        String nameChanged = hurtParcer.getNameChanged();
//        String milkCaseAndOccurrenceChanged = hurtParcer.getMilkCaseAndOccurrenceChanged();




        String nameChanged = hurtParcer.changeName(hurtLockerData);
        String milkCaseAndOccurrenceChanged = hurtParcer.changeMilkCaseAndRecordOccurrences(nameChanged);
//        String milkPricesChanged = hurtParcer.countAndRemoveDuplicateMilkPrices(milkCaseAndOccurrenceChanged);


        System.out.println(hurtParcer.changeName(hurtLockerData));
        System.out.println(hurtParcer.changeMilkCaseAndRecordOccurrences(nameChanged));
//        System.out.println(hurtParcer.countAndRemoveDuplicateMilkPrices(milkCaseAndOccurrenceChanged));

    }
}
