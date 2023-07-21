package dev.carl.eggwars.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RequiredArgsConstructor
public abstract class AbstractLoad {
    private final String json;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void load() {
        JsonArray a;
        try {
            FileReader fileReader = new FileReader(json);
            a = (JsonArray) JsonParser.parseReader(fileReader);
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException OCCURED" + e);
            return;
        }

        for (Object o : a)
        {
           objectsLoad((JsonObject) o);
        }
    }

    public abstract void objectsLoad(JsonObject person);
}
