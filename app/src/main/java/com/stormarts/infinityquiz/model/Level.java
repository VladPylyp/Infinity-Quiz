package com.stormarts.infinityquiz.model;


import java.util.HashMap;
import java.util.Map;

public final class Level {

    public static final String EASY_KEY = "easy_level";
    public static final String MID_KEY = "mid_level";
    public static final String HARD_KEY = "hard_level";
    public static final String BONUS_KEY = "bonus_level";

    public static final String SELECTED = "selected_level";

    private static final Map<Integer, String> levelsMapStrings;
    private static final Map<String, Integer> levelsMapInt;
    private static final Map<String, Float> coefficientMap;

    public static final int EASY = 101;
    public static final int MID = 102;
    public static final int HARD = 103;

    public static final float EASY_COEFFICIENT = 0.1f;
    public static final float MID_COEFFICIENT = 0.15f;
    public static final float HARD_COEFFICIENT = 0.2f;


    public static final int BONUS = 110;


    public static String getLevelString(int level){
        return levelsMapStrings.get(level);
    }

    public static int getLevelInt(String level){
        return levelsMapInt.get(level);
    }

    public static boolean isLevelExist(int level){
        return levelsMapStrings.containsKey(level);
    }

    public float getCoefficient(String level){
        return coefficientMap.get(level);
    }



   static{
       (levelsMapStrings = new HashMap()).put(EASY, EASY_KEY);
       levelsMapStrings.put(MID, MID_KEY);
       levelsMapStrings.put(HARD,HARD_KEY);

       (levelsMapInt = new HashMap()).put(EASY_KEY,EASY);
       levelsMapInt.put(MID_KEY,MID);
       levelsMapInt.put(HARD_KEY,HARD);

       (coefficientMap = new HashMap()).put(EASY_KEY,EASY_COEFFICIENT);
       coefficientMap.put(MID_KEY,MID_COEFFICIENT);
       coefficientMap.put(HARD_KEY,HARD_COEFFICIENT);

   }

}
