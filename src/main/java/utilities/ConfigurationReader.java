package utilities;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ConfigurationReader {

    private static Yaml yaml = new Yaml();
    private static Map<String, Object> data;

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.yml");
            data = yaml.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class.");
            e.printStackTrace();
        }
    }

    public static Object getProperty(String keyword){
        return data.get(keyword);
    }
}