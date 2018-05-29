package com.stormarts.infinityquiz;

public abstract class ResourceManager {

     public abstract String  loadJsonFromAsset(String fileName);
     public abstract <T> T getJavaObject(Class<T> type, String jsonString);

     public static String constructPassToJsonLevel(String fileName, String locale){
          return "levels/" + fileName + "_" + locale + ".json";
     }
}
