package com.stormarts.infinityquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("coins")
    @Expose
    private int coins;

    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("allowedLevel")
    @Expose
    private int allowedLevel;


    public User() {
    }

    public User(String uuid, String nickname, int coins, int level, int score) {
        this.nickname = nickname;
        this.coins = coins;
        this.allowedLevel = level;
        this.uuid = uuid;
        this.score = score;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAllowedLevel() {
        return allowedLevel;
    }

    public void setAllowedLevel(int allowedLevel) {
        this.allowedLevel = allowedLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                " uuid = \"" + uuid + "\"" +
                ", score = " + score +
                ", coins = " + coins +
                ", nickname = \"" + nickname + "\"" +
                ", allowedLevel = " + allowedLevel +
                " }";
    }
}
