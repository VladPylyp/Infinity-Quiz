package com.stormarts.infinityquiz.model;

public  class GameAuth {

    private User currentUser;

    private static GameAuth instance  = null;

    private GameAuth(){}

    public static synchronized GameAuth getInstance(){
     if(instance == null){
         instance = new GameAuth();
     }
     return (instance);
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }
    public void setNewUser(String uuid, String nickname){
        if(currentUser== null) {
            currentUser = new User(uuid, nickname, 0, Level.EASY,0);
        } else{
            currentUser.setNickname(nickname);
            currentUser.setCoins(0);
            currentUser.setUuid(uuid);
            currentUser.setAllowedLevel(Level.EASY);
            currentUser.setScore(0);
        }
    }

}
