package jsonparser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONParser {
    private final static Logger log = Logger.getLogger(JSONParser.class.getName());

    public JsonObject getJSONObject() throws FileNotFoundException {
        log.debug(" getJSONObject is called");
        JsonObject jsonObject = JsonParser.parseReader(new FileReader("src/main/resources/input.json")).getAsJsonObject();
        log.debug("getJSONObject completed and return"+jsonObject.getAsString());
        return  jsonObject;
    }
}
