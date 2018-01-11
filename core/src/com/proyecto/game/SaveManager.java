package com.proyecto.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

/**
 * Created by frederick on 22/06/16.
 */
public class SaveManager {

    private FileHandle file = Gdx.files.local("bin/scores.json");
    private Save save = new Save();

    public Save getSave(){
        Save save = new Save();
        if(file.exists()){
            Json json = new Json();
            save = json.fromJson(Save.class, file.readString());
        }
        return save;
    }

    public void saveToJson(){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        file.writeString(json.prettyPrint(save), false);
    }

    public <T> T loadDataValue(String key, Class type){
        if(save.data.containsKey(key))
            return (T) save.data.get(key);
        else
            return null;
    }

    public void saveDataValue(String key, Object object){
        save.data.put(key, object);
        saveToJson();
    }
}
