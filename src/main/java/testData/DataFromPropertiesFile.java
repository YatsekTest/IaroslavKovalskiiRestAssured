package testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static constants.DataFilePath.AUTH_DATA_FILE_PATH;

public class DataFromPropertiesFile {

    private static Properties data;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(AUTH_DATA_FILE_PATH)) {
            data = new Properties();
            data.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Data file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Data can't be loaded.");
        }
    }

    public static Properties getData() {
        return data;
    }
}
