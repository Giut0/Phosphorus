package di.uniba;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")

public class Utils {

    public static Map<String, Object> readJSON(String filePath) throws StreamReadException, DatabindException, IOException {
        File JSON_SOURCE = new File(filePath);
        Map<String, Object> jsonFile = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);

        return jsonFile;
    }

}
