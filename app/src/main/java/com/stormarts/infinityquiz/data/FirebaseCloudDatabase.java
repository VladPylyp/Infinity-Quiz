package com.stormarts.infinityquiz.data;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stormarts.infinityquiz.model.GameAuth;
import com.stormarts.infinityquiz.model.User;

import org.jetbrains.annotations.NotNull;


public class FirebaseCloudDatabase implements CloudDatabase {
    private static final String LOGTAG = FirebaseCloudDatabase.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    DatabaseUserEventListener listener;


    public FirebaseCloudDatabase() {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);

    }

    public void setUserDataListener(DatabaseUserEventListener listener){
        this.listener = listener;
    }

    public void setNewUser(User user){
        userRef.setValue(GameAuth.getInstance().getCurrentUser());
    }


    @Override
    public void getUser(@NotNull String uuid) {
        userRef = database.getReference("users").child(uuid);
        userRef.keepSynced(true);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(LOGTAG, "onDataChange: " + dataSnapshot.toString());
                if(dataSnapshot.getKey().contains(uuid)
                        && dataSnapshot.getValue()== null){
                    Log.d(LOGTAG, "onDataChange: (if st.)" );
                    listener.onUserNotExist();
                } else{
                    listener.onUserExist(dataSnapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onError(new CloudDatabaseError(databaseError.getCode(), databaseError.getMessage(), databaseError.toString()));
            }
        });

    }
}
