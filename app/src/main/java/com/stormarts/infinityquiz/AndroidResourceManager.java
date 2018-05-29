package com.stormarts.infinityquiz;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;



public class AndroidResourceManager extends ResourceManager {
    private static final String TAG = AndroidResourceManager.class.getCanonicalName();

    private AssetManager assets;
    private Gson gson;


    public AndroidResourceManager(AssetManager assets) {
        this.assets = assets;
        gson = new GsonBuilder().create();
    }


    @Override
    public String loadJsonFromAsset(String fileName) {
        String json;
        try {
            InputStream is = assets.open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public String loadJsonFromRecource(Resources resources, int id){
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e(TAG, "Unhandled exception while using openJsonRecource", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e(TAG, "Unhandled exception while using openJsonRecource", e);
            }
        }

        return writer.toString();
    }

    public <T> T getJavaObject(Class<T> type, String jsonString) {
        return gson.fromJson(jsonString, type);
    }

}
