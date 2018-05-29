package com.stormarts.infinityquiz.data;


import com.stormarts.infinityquiz.model.User;

public interface CloudDatabase {

        void getUser(String uuid);
        void setNewUser(User user);
        void setUserDataListener(DatabaseUserEventListener listener);
}
