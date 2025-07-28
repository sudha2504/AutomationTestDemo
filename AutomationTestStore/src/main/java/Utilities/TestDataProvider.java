package Utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "Currency")
    public Object[][] provideData() {
        return new Object[][]{
                {"Dollar", "$"},
                {"Pound", "£"},
                {"Euro", "€"}
        };
    }

    public List<LinkedHashMap<String, String>> readData(File filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileInputStream jsonFile = new FileInputStream(filepath);
            // Read JSON array and map each object to a HashMap
            List<LinkedHashMap<String, String>> list = mapper.readValue( jsonFile, new TypeReference<List<LinkedHashMap<String, String>>>() { });
            // Print the result
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @DataProvider
    public Object[][] getUserData(){

        List<LinkedHashMap<String, String>> mapdata = readData(new File(System.getProperty("user.dir") + "/src/main/java/TestData/NewUserInfo.json"));
        return new Object[][]{{mapdata.get(0)}};
    }


}