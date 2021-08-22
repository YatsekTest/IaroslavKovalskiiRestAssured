package testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataFromPropertiesFile {

    private static Properties data;

    public static Properties getData(String dataFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(dataFilePath)) {
            data = new Properties();
            data.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Data can't be loaded.");
        }
        return data;
    }

}
