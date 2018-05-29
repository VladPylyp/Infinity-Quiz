package com.stormarts.infinityquiz.data;

import com.stormarts.infinityquiz.model.User;

public interface DatabaseUserEventListener {

    void onUserExist(User user);
    void onUserNotExist();

    void onError(CloudDatabaseError error);

}
